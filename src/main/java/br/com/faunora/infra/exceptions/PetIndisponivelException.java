package br.com.faunora.infra.exceptions;

public class PetIndisponivelException extends RuntimeException {
    private static final String defaultMessage = "o pet já tem um agendamento no dia e horário fornecidos";

    public PetIndisponivelException() {
        super(defaultMessage);
    }

    public PetIndisponivelException(String message) {
        super(message);
    }
}
