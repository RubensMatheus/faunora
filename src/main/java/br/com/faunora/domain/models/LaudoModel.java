package br.com.faunora.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_laudos")
@Data
public class LaudoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laudo_id")
    private Long id;

    @Column(name = "laudo_descricao", nullable = false)
    private String prescricao;

    @Column(name = "laudo_validade", nullable = false)
    private LocalDate validade;

    @JsonManagedReference
    @JoinColumn(name = "exame_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private ExameModel exame;
}

