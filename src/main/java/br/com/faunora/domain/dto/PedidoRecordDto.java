package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PedidoRecordDto(@NotNull UUID produtoId) {
}
