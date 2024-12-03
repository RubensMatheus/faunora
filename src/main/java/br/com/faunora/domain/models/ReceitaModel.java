package br.com.faunora.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_receitas")
@Data
public class ReceitaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receita_id")
    private Long id;

    @Column(name = "receita_prescricao", nullable = false)
    private String prescricao;

    @Column(name = "receita_recomendacao", nullable = false)
    private String recomendacao;

    @JsonManagedReference
    @JoinColumn(name = "consulta_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private ConsultaModel consulta;
}
