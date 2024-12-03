package br.com.faunora.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserEmailRecordDto(@NotBlank @Email String novoEmail, @NotBlank String senha) {
}
