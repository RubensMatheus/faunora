package br.com.faunora.services.impl;

import br.com.faunora.domain.models.UserModel;
import br.com.faunora.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Iniciando busca do usuário pelo email: {}", email);

        // Busca o usuário no repositório
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("Usuário com email '{}' não encontrado.", email);
                    return new UsernameNotFoundException("Usuário não encontrado");
                });

        logger.info("Usuário encontrado: {} - Tipo: {}", userModel.getEmail(), userModel.getTipo());

        // Retorna um objeto UserDetails para autenticação
        return User.builder()
                .username(userModel.getEmail())
                .password(userModel.getSenha()) // Senha já criptografada
                .roles(userModel.getTipo().name()) // Define as roles do usuário
                .build();
    }
}
