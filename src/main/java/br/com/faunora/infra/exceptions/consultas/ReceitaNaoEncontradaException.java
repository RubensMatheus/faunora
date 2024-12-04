package br.com.faunora.infra.exceptions.consultas;

public class ReceitaNaoEncontradaException extends RuntimeException{
    private static final String defaultMessage = "receita não encontrado";

    public ReceitaNaoEncontradaException() {
        super(defaultMessage);
    }

    public ReceitaNaoEncontradaException(String message) {
        super(message);
    }
}
