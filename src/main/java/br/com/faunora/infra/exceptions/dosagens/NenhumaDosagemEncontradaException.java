package br.com.faunora.infra.exceptions.dosagens;

public class NenhumaDosagemEncontradaException extends RuntimeException {
    private static final String defaultMessage = "nenhuma dosagem encontrada";

    public NenhumaDosagemEncontradaException() {
        super(defaultMessage);
    }

    public NenhumaDosagemEncontradaException(String message) {
        super(message);
    }
}
