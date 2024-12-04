package br.com.faunora.domain.dto.dosagens;

import br.com.faunora.domain.enums.DosagemTipo;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DosagemRecordDto(
        @NotNull Long pacienteId,
        @NotNull DosagemTipo tipo,
        @NotNull LocalDate data,
        @NotNull LocalTime hora
        ) {
}
