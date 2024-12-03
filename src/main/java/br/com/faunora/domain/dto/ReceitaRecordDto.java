package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReceitaRecordDto(
        @NotNull String prescricao,
        @NotNull String recomendacao,
        @NotNull Long consultaId
        ) {
}
