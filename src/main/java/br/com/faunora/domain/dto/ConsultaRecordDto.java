package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;

public record ConsultaRecordDto(
        @NotNull Long pacienteId,
        @NotNull Long veterinarioId,
        @NotNull LocalDate data,
        @NotNull Instant hora
        ) {
}
