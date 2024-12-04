package br.com.faunora.infra.exceptions.exames;

public class LaudoNaoEncontradoException extends RuntimeException {
    private static final String defaultMessage = "laudo não encontrado";

    public LaudoNaoEncontradoException() {
        super(defaultMessage);
    }

    public LaudoNaoEncontradoException(String message) {
        super(message);
    }
}
