package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public record RestErroRecordDto(@NotNull HttpStatus status, @NotBlank String message) {
}
