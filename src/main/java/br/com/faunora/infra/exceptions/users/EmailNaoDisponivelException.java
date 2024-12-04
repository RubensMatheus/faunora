package br.com.faunora.infra.exceptions.users;

public class EmailNaoDisponivelException extends RuntimeException {
    private static final String defaultMessage = "email fornecido já está cadastrado";

    public EmailNaoDisponivelException() {
        super(defaultMessage);
    }

    public EmailNaoDisponivelException(String message) {
        super(message);
    }
}
