package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRecordDto(@NotBlank String email) {
}
