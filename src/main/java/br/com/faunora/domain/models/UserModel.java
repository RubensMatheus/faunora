package br.com.faunora.domain.models;

import br.com.faunora.domain.enums.UserTipo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Data
public class UserModel implements Serializable {
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
    private String senha; // Senha com hash

    @Column(name = "user_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTipo tipo; // CLIENTE ou VETERINARIO

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<PetModel> pets;

}
