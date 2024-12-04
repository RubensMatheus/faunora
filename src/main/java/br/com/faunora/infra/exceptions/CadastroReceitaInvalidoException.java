package br.com.faunora.infra.exceptions;

public class CadastroReceitaInvalidoException extends RuntimeException {
    private static final String defaultMessage = "cadastro de receita para consultas futuras inválido";

    public CadastroReceitaInvalidoException() {
        super(defaultMessage);
    }

    public CadastroReceitaInvalidoException(String message) {
        super(message);
    }
}
