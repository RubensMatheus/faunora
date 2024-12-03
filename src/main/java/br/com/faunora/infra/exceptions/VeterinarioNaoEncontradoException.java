package br.com.faunora.infra.exceptions;

public class VeterinarioNaoEncontradoException extends RuntimeException{
    private static final String defaultMessage = "veterinário não encontrado";

    public VeterinarioNaoEncontradoException() {
        super(defaultMessage);
    }

    public VeterinarioNaoEncontradoException(String message) {
        super(message);
    }
}
