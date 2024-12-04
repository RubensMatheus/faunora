package br.com.faunora.domain.dto.consultas;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaRecordDto(
        @NotNull Long pacienteId,
        @NotNull Long veterinarioId,
        @NotNull LocalDate data,
        @NotNull LocalTime hora
        ) {
}
