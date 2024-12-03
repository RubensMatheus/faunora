package br.com.faunora.infra.security;

import br.com.faunora.domain.models.UserModel;
import br.com.faunora.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.faunora.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    UserRepository userRepository;
    private final ArrayList<String> freeEndpoints = new ArrayList<>();

    @PostConstruct
    private void init() {
        freeEndpoints.add("/users/registrar");
        freeEndpoints.add("/users/login");
        freeEndpoints.add("/users/redefinir-senha");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (isFilterSkippable(request, freeEndpoints)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);

        if (token != null) {
            String login = tokenService.verifyToken(token);

            if (login != null) {
                Optional <UserModel> userModel = userRepository.findByEmail(login);

                if (userModel.isEmpty()) {
                    /*falha ao recuperar usuário, não é possível autenticar*/
                    filterChain.doFilter(request, response);
                    return;
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userModel.get(), null, userModel.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }

        /*token não fornecido*/
        return null;
    }

    public boolean isFilterSkippable(HttpServletRequest request, ArrayList<String> freeEndpoints) {
        for (String freeEndpoint : freeEndpoints) {
            if (request.getRequestURI().startsWith(freeEndpoint) && request.getRequestURI().endsWith(freeEndpoint)) {
                return true;
            }
        }
        return false;
    }
}
