package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.ExameTipo;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;

public record ExameRecordDto(
        @NotNull Long pacienteId,
        @NotNull ExameTipo tipo,
        @NotNull LocalDate data,
        @NotNull Instant hora
        ) {
}