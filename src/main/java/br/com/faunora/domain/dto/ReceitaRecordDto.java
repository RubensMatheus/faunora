package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReceitaRecordDto(
        @NotBlank String prescricao,
        @NotBlank String recomendacao,
        @NotNull Long consultaId
        ) {
}
