package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserNameRecordDto(@NotBlank String senhaAtual, @NotBlank String novoNome, @NotBlank String novoSobrenome) {
}
