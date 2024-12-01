package br.com.faunora.services;

import br.com.faunora.domain.dto.UserRecordDto;
import br.com.faunora.domain.models.UserModel;
import br.com.faunora.infra.exceptions.NenhumUsuarioEncontradoException;
import br.com.faunora.infra.exceptions.UsuarioNaoEncontradoException;
import br.com.faunora.infra.security.JWTUtils;
import br.com.faunora.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JWTUtils jwtUtils;

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
    public Map<String, Object> updateUser(Long id, UserRecordDto userRecordDto) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException());

        // Iterar sobre todos os campos do DTO
        for (Field field : userRecordDto.getClass().getDeclaredFields()) {
            field.setAccessible(true); // Permite acessar campos privados
            try {
                // Verifica se o campo no DTO não é nulo
                Object value = field.get(userRecordDto);
                if (value != null && !(value instanceof String && ((String) value).isBlank())) {
                    String fieldName = field.getName();

                    if ("senha".equals(fieldName)) {
                        // Tratamento especial para senha
                        String encodedPassword = passwordEncoder.encode(value.toString());
                        Method setter = findSetter(userModel.getClass(), fieldName, String.class);
                        if (setter != null) {
                            setter.invoke(userModel, encodedPassword);
                        }
                    } else {
                        // Atualiza outros campos
                        Method setter = findSetter(userModel.getClass(), fieldName, field.getType());
                        if (setter != null) {
                            setter.invoke(userModel, value);
                        }
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Erro ao atualizar o campo: " + field.getName(), e);
            }
        }

        // Salva o usuário atualizado
        userRepository.save(userModel);

        // Gera um novo token JWT baseado no email atualizado (ou existente)
        String newToken = jwtUtils.generateToken(userModel.getEmail());

        // Retorna um mapa com o usuário atualizado e o novo token
        Map<String, Object> response = new HashMap<>();
        response.put("user", userModel);
        response.put("token", newToken);

        return response;
    }


    // Método para encontrar o setter correspondente
    private Method findSetter(Class<?> clazz, String fieldName, Class<?> paramType) {
        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            return clazz.getMethod(setterName, paramType);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Transactional
    public void deleteById(Long id) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(UsuarioNaoEncontradoException::new);

        userRepository.delete(userModel);
    }
}
