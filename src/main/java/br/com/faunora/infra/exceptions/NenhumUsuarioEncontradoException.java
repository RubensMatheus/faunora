package br.com.faunora.infra.exceptions;

public class NenhumUsuarioEncontradoException extends RuntimeException {
    private static final String defaultMessage = "nenhum usuário encontrado";

    public NenhumUsuarioEncontradoException() {
        super(defaultMessage);
    }

    public NenhumUsuarioEncontradoException(String message) {
        super(message);
    }
}
