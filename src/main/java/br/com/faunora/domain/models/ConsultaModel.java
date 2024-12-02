package br.com.faunora.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_consultas", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "consulta_data", "consulta_hora"}), @UniqueConstraint(columnNames = {"pet_id", "consulta_data", "consulta_hora"})})
@Data
public class ConsultaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consulta_id")
    private Long id;

    @JoinColumn(name = "pet_id", nullable = false)
    @ManyToOne
    private PetModel paciente;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private UserModel veterinario;

    @Column(name = "consulta_data", nullable = false)
    private LocalDate data;

    @Column(name = "consulta_hora", nullable = false)
    private Instant hora;

    @JoinColumn(name = "receita_id", nullable = false, unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private ReceitaModel receita;
}
