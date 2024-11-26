package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRecordDto(
        @NotBlank(message = "O nome é obrigatório.") String nome,
        @NotBlank(message = "O sobrenome é obrigatório.") String sobrenome,
        @NotBlank(message = "O email é obrigatório.") 
        @Email(message = "O email deve ser válido.") String email,
        @NotBlank(message = "A senha é obrigatória.") 
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.") String senha,
        @NotBlank(message = "A confirmação de senha é obrigatória.") String confirmarSenha,
        @NotNull(message = "O tipo de usuário é obrigatório.") UserType tipo
) {
}
