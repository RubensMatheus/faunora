package br.com.faunora.domain.models;

import br.com.faunora.domain.enums.UserTipo;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "tb_users")
@Data
public class UserModel implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_nome", nullable = false)
    private String nome;

    @Column(name = "user_sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_senha", nullable = false)
    private String senha;

    @Column(name = "user_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTipo tipo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + tipo.name().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
