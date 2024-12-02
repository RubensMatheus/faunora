package br.com.faunora.domain.models;

import br.com.faunora.domain.enums.ExameTipo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_exames")
@Data
public class ExameModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exame_id")
    private Long id;

    @JoinColumn(name = "pet_id", nullable = false)
    @ManyToOne
    private PetModel paciente;

    @Column(name = "exame_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExameTipo tipo;

    @Column(name = "exame_data", nullable = false)
    private LocalDate data;

    @Column(name = "exame_hora", nullable = false)
    private Instant hora;

    @JoinColumn(name = "laudo_id")
    @OneToOne(cascade = CascadeType.ALL)
    private LaudoModel laudo;
}
