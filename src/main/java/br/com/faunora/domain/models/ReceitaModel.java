package br.com.faunora.domain.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_receitas")
@Data
public class ReceitaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "receita_id")
    private UUID id;

    @Column(name = "receita_prescricao", nullable = false)
    private String prescricao;

    @Column(name = "receita_recomendacao")
    private String recomendacao;

    @JoinColumn(name = "consulta_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private ConsultaModel consulta;
}
