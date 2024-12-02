package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.PedidoStatus;
import jakarta.validation.constraints.NotNull;

public record UpdatePedidoRecordDto(@NotNull Long produtoId, @NotNull PedidoStatus status) {
}
