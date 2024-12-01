package br.com.faunora.domain.models;

import br.com.faunora.domain.enums.ProdutoTipo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")
@Data
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private UUID id;

    @Column(name = "produto_nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "produto_preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "produto_marca", nullable = false)
    private String marca;

    @Column(name = "produto_descricao", nullable = false)
    private String descricao;

    @Column(name = "produto_categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProdutoTipo categoria;

    /*adicionar imagem de amostragem*/
}
