package br.com.faunora.infra.exceptions.dosagens;

public class DosagemNaoEncontradaException extends RuntimeException {
    private static final String defaultMessage = "dosagem não encontrada";

    public DosagemNaoEncontradaException() {
        super(defaultMessage);
    }

    public DosagemNaoEncontradaException(String message) {
        super(message);
    }
}
