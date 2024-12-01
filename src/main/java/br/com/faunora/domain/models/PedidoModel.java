package br.com.faunora.domain.models;

import br.com.faunora.domain.enums.PedidoStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
@Data
public class PedidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pedido_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel produto;

    @Column(name = "pedido_status")
    private PedidoStatus status;

    @Column(name = "pedido_data_criacao")
    private LocalDate dataCriacao;
}
