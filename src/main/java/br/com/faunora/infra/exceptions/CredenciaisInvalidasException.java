package br.com.faunora.infra.exceptions;

public class CredenciaisInvalidasException extends RuntimeException {
    private static final String defaultMessage = "credenciais inválidas";

    public CredenciaisInvalidasException() {
        super(defaultMessage);
    }

    public CredenciaisInvalidasException(String message) {
        super(message);
    }
}
