package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotNull;

public record PedidoRecordDto(@NotNull Long produtoId) {
}
