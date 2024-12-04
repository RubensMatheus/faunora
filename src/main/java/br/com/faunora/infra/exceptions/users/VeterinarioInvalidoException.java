package br.com.faunora.infra.exceptions.users;

public class VeterinarioInvalidoException extends RuntimeException{
    private static final String defaultMessage = "veterinário fornecido não válido";

    public VeterinarioInvalidoException() {
        super(defaultMessage);
    }

    public VeterinarioInvalidoException(String message) {
        super(message);
    }
}
