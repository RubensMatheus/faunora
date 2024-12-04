package br.com.faunora.domain.dto.users;

import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRecordDto(@NotBlank String email) {
}
