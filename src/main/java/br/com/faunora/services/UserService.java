package br.com.faunora.services;

import br.com.faunora.domain.dto.users.*;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.users.*;
import br.com.faunora.infra.security.TokenService;
import br.com.faunora.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;

    @Transactional
    public void saveUser(UserRecordDto userRecordDto) {
        verificarEmailValido(userRecordDto.email());

        if (!userRecordDto.senha().equals(userRecordDto.confirmarSenha())) {
            throw new SenhasNaoCoincidemException();
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel.setSenha(passwordEncoder.encode(userRecordDto.senha()));

        userRepository.save(userModel);
    }

    public LoginResponseRecordDto validateUser(LoginRequestRecordDto loginRequestRecordDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestRecordDto.email(), loginRequestRecordDto.senha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserModel userModel = userRepository.findByEmail(loginRequestRecordDto.email())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        String token = tokenService.generateToken(userModel);

        return new LoginResponseRecordDto("login realizado com sucesso", token);
    }

    public UserModel findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public List<UserModel> findAll() {
        List<UserModel> userModels = userRepository.findAll();

        if (userModels.isEmpty()) {
            throw new NenhumUsuarioEncontradoException();
        }

        return userModels;
    }

    @Transactional
    public UpdateUserResponseRecordDto updateUser(Long id, UserRecordDto userRecordDto) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        verificarEmailValido(userRecordDto.email());

        userModel.setEmail(userRecordDto.email());
        userModel.setSenha(passwordEncoder.encode(userRecordDto.senha()));
        userModel.setNome(userRecordDto.nome());
        userModel.setSobrenome(userRecordDto.sobrenome());
        userModel.setTipo(userRecordDto.tipo());

        userRepository.save(userModel);

        String token = tokenService.generateToken(userModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && Objects.equals(authentication.getName(), userModel.getEmail())) {
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userModel, null, userModel.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }

        return new UpdateUserResponseRecordDto(userModel.getEmail(), token);
    }

    @Transactional
    public UpdateUserResponseRecordDto updateSenha(UpdateUserPasswordRecordDto updateUserPasswordRecordDto) {
        UserModel userModel = recuperarUsuarioLogado();

        if (!passwordEncoder.matches(updateUserPasswordRecordDto.senhaAtual(), userModel.getSenha())) {
            throw new CredenciaisInvalidasException("senha atual incorreta");
        }

        if (!Objects.equals(updateUserPasswordRecordDto.senhaNova(), updateUserPasswordRecordDto.confirmacaoSenhaNova())) {
            throw new SenhasNaoCoincidemException();
        }

        userModel.setSenha(passwordEncoder.encode(updateUserPasswordRecordDto.senhaNova()));
        userRepository.save(userModel);

        String token = tokenService.generateToken(userModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && Objects.equals(authentication.getName(), userModel.getEmail())) {
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userModel, null, userModel.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }

        return new UpdateUserResponseRecordDto(userModel.getEmail(), token);
    }

    @Transactional
    public UpdateUserResponseRecordDto updateNome(UpdateUserNameRecordDto updateUserNameRecordDto) {
        UserModel userModel = recuperarUsuarioLogado();

        if (!passwordEncoder.matches(updateUserNameRecordDto.senhaAtual(), userModel.getSenha())) {
            throw new CredenciaisInvalidasException("senha fornecida incorreta");
        }

        userModel.setNome(updateUserNameRecordDto.novoNome());
        userModel.setSobrenome(updateUserNameRecordDto.novoSobrenome());

        userRepository.save(userModel);

        String token = tokenService.generateToken(userModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && Objects.equals(authentication.getName(), userModel.getEmail())) {
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userModel, null, userModel.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }

        return new UpdateUserResponseRecordDto(userModel.getEmail(), token);
    }

    @Transactional
    public UpdateUserResponseRecordDto updateEmail(UpdateUserEmailRecordDto updateUserEmailRecordDto) {
        UserModel userModel = recuperarUsuarioLogado();

        if (!passwordEncoder.matches(updateUserEmailRecordDto.senha(), userModel.getSenha())) {
            throw new CredenciaisInvalidasException("senha fornecida incorreta");
        }

        userModel.setEmail(updateUserEmailRecordDto.novoEmail());

        userRepository.save(userModel);

        String token = tokenService.generateToken(userModel);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && Objects.equals(authentication.getName(), userModel.getEmail())) {
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userModel, null, userModel.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }

        return new UpdateUserResponseRecordDto(userModel.getEmail(), token);
    }

    public void esqueceuSenha(String email) {
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        String resetToken = tokenService.generateToken(userModel);
        emailService.sendPasswordResetEmail(email, resetToken);
    }

    @Transactional
    public void redefinirSenha(ResetPasswordRecordDto resetPasswordRecordDto) {
        if (!resetPasswordRecordDto.novaSenha().equals(resetPasswordRecordDto.confirmaNovaSenha())) {
            throw new SenhasNaoCoincidemException();
        }

        String login = tokenService.verifyToken(resetPasswordRecordDto.token());

        UserModel userModel = userRepository.findByEmail(login)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        userModel.setSenha(passwordEncoder.encode(resetPasswordRecordDto.novaSenha()));

        userRepository.save(userModel);
    }

    @Transactional
    public void deleteById(Long id) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        userRepository.deleteById(id);
    }

    public void verificarEmailValido(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailNaoDisponivelException();
        }
    }

    public UserModel recuperarUsuarioLogado() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new UsuarioNaoEncontradoException();
        }

        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }
}
