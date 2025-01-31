package br.com.faunora.domain.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestRecordDto(
        @NotBlank @Email String email,
        @NotBlank String senha
) {
}
