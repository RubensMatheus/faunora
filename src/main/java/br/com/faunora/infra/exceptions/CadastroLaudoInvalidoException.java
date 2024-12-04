package br.com.faunora.infra.exceptions;

public class CadastroLaudoInvalidoException extends RuntimeException {
    private static final String defaultMessage = "cadastro de laudo para exames futuros inválido";

    public CadastroLaudoInvalidoException() {
        super(defaultMessage);
    }

    public CadastroLaudoInvalidoException(String message) {
        super(message);
    }
}
