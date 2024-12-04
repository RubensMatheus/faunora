package br.com.faunora.domain.dto.produtos;

import jakarta.validation.constraints.NotNull;

public record PedidoRecordDto(@NotNull Long produtoId) {
}
