package br.com.faunora.services;

import br.com.faunora.domain.dto.UserRecordDto;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.NenhumUsuarioEncontradoException;
import br.com.faunora.infra.exceptions.UsuarioNaoEncontradoException;
import br.com.faunora.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserModel saveUser(UserRecordDto userRecordDto) {
        if (userRepository.existsByEmail(userRecordDto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (!userRecordDto.senha().equals(userRecordDto.confirmarSenha())) {
            throw new IllegalArgumentException("As senhas não coincidem.");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel.setSenha(passwordEncoder.encode(userRecordDto.senha()));

        return userRepository.save(userModel);
    }

    public UserModel findById(UUID id) {
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
    public UserModel updateUser(UUID id, UserRecordDto userRecordDto) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        // Atualiza os campos permitidos
        BeanUtils.copyProperties(userRecordDto, userModel, "senha"); // Exclui "senha" para evitar sobrescrita acidental
        if (userRecordDto.senha() != null && !userRecordDto.senha().isBlank()) {
            userModel.setSenha(passwordEncoder.encode(userRecordDto.senha()));
        }

        return userRepository.save(userModel);
    }

    @Transactional
    public void deleteById(UUID id) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        userRepository.delete(userModel);
    }
}
