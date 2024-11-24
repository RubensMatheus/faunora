package br.com.faunora.domain.models;

import br.com.faunora.domain.enums.PetSexo;
import br.com.faunora.domain.enums.PetTipo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_pets")
@Data
public class PetModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pet_id")
    private UUID id;

    @Column(name = "pet_nome", nullable = false)
    private String nome;

    @Column(name = "pet_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetTipo tipo;

    @Column(name = "pet_sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetSexo sexo;

    @Column(name = "pet_peso", nullable = false)
    private double peso;

    @Column(name = "pet_data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private UserModel tutor;

    /*adicionar foto de perfil*/
}
