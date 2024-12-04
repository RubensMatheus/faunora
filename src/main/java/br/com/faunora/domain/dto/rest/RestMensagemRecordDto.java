package br.com.faunora.domain.dto.rest;

import jakarta.validation.constraints.NotBlank;

public record RestMensagemRecordDto(@NotBlank String message) {
}
