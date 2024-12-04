package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.UserTipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRecordDto(
        @NotBlank(message = "o nome é obrigatório") String nome,
        @NotBlank(message = "o sobrenome é obrigatório") String sobrenome,
        @NotBlank(message = "o email é obrigatório")
        @Email(message = "o email deve ser válido") String email,
        @NotBlank(message = "a senha é obrigatória")
        @Size(min = 6, message = "a senha deve ter pelo menos 6 caracteres") String senha,
        @NotBlank(message = "a confirmação de senha é obrigatória") String confirmarSenha,
        @NotNull(message = "o tipo de usuário é obrigatório") UserTipo tipo) {
}
