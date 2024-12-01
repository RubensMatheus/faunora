package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.PedidoStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdatePedidoRecordDto(@NotNull UUID produtoId, @NotNull PedidoStatus status) {
}
