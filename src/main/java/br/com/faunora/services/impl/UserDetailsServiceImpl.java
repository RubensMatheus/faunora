package br.com.faunora.services.impl;

import br.com.faunora.domain.models.UserModel;
import br.com.faunora.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Cria um objeto UserDetails com as informações necessárias para autenticação
        return User.builder()
        		.username(userModel.getEmail())
        		.password(userModel.getSenha()) // Senha já hasheada
                .roles(userModel.getTipo().name()) // Roles baseadas no tipo de usuário (CLIENTE ou VETERINARIO)
                .build();
    }
}
