package br.com.faunora.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record RestMensagemRecordDto(@NotBlank String message) {
}
