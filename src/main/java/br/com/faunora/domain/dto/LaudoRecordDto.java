package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LaudoRecordDto(
        @NotNull String prescricao,
        @NotNull LocalDate validade,
        @NotNull Long exameId
        ) {
}
