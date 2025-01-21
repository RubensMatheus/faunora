package br.com.faunora.domain.dto.users;

import br.com.faunora.domain.enums.UserTipo;
import jakarta.validation.constraints.NotBlank;

public record LoginResponseRecordDto(
        @NotBlank String message,
        @NotBlank String token,
        @NotBlank UserTipo userTipo,
        @NotBlank Long id) {
}
