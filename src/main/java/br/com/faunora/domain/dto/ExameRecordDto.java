package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.ExameTipo;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ExameRecordDto(
        @NotNull UUID pacienteId,
        @NotNull ExameTipo tipo,
        @NotNull LocalDate data,
        @NotNull Instant hora,
        @NotNull Long laudoId
        ) {
}