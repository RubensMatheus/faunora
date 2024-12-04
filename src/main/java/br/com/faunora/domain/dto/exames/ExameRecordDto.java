package br.com.faunora.domain.dto.exames;

import br.com.faunora.domain.enums.ExameTipo;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ExameRecordDto(
        @NotNull Long pacienteId,
        @NotNull ExameTipo tipo,
        @NotNull LocalDate data,
        @NotNull LocalTime hora
        ) {
}