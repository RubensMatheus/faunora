package br.com.faunora.domain.dto;

import br.com.faunora.domain.enums.PetSexo;
import br.com.faunora.domain.enums.PetTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PetRecordDto(@NotBlank String nome, @NotNull PetTipo tipo, @NotNull PetSexo sexo, @NotNull @Positive double peso, @NotNull @Past LocalDate dataNascimento) {
}
