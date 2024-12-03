package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserResponseRecordDto(@NotBlank String email, @NotBlank String token) {
}
