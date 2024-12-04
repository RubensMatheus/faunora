package br.com.faunora.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "consulta_data", nullable = false)
    private LocalDate data;

    @Column(name = "consulta_hora", nullable = false)
    private LocalTime hora;

    @JsonBackReference
    @JoinColumn(name = "receita_id", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private ReceitaModel receita;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private UserModel veterinario;
}
