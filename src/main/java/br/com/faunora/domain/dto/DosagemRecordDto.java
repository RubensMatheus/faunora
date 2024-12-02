package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.DosagemTipo;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;

public record DosagemRecordDto(
        @NotNull Long pacienteId,
        @NotNull DosagemTipo tipo,
        @NotNull LocalDate data,
        @NotNull Instant hora
        ) {
}
