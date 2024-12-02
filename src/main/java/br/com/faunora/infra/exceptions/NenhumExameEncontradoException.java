package br.com.faunora.infra.exceptions;

public class NenhumExameEncontradoException extends RuntimeException {
    private static final String defaultMessage = "nenhum exame encontrado";

    public NenhumExameEncontradoException() {
        super(defaultMessage);
    }

    public NenhumExameEncontradoException(String message) {
        super(message);
    }
}
