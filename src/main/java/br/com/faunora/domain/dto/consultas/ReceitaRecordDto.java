package br.com.faunora.domain.dto.consultas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReceitaRecordDto(
        @NotBlank String prescricao,
        @NotBlank String recomendacao,
        @NotNull Long consultaId
        ) {
}
