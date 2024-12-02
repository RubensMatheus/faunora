package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.DosagemTipo;
import br.com.faunora.domain.models.PetModel;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record DosagemRecordDto(
        @NotNull UUID pacienteId,
        @NotNull DosagemTipo tipo,
        @NotNull LocalDate data,
        @NotNull Instant hora
        ) {
}
