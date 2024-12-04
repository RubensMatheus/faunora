package br.com.faunora.domain.dto.users;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserPasswordRecordDto(@NotBlank String senhaAtual, @NotBlank String senhaNova, @NotBlank String confirmacaoSenhaNova) {
}
