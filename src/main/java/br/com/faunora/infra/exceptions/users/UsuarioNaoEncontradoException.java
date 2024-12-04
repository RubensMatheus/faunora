package br.com.faunora.infra.exceptions.users;

public class UsuarioNaoEncontradoException extends RuntimeException {
    private static final String defaultMessage = "usuário não encontrado";

    public UsuarioNaoEncontradoException() {
        super(defaultMessage);
    }

    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
