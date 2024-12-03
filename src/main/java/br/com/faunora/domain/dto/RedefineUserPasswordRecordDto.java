package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record RedefineUserPasswordRecordDto(@NotBlank String senha, @NotBlank String confirmaSenha) {
}
