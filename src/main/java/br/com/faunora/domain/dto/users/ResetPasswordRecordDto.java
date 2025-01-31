package br.com.faunora.domain.dto.users;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRecordDto(@NotBlank String token, @NotBlank String novaSenha, @NotBlank String confirmaNovaSenha) {
}
