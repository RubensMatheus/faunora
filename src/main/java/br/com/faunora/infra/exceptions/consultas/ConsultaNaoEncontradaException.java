package br.com.faunora.infra.exceptions.consultas;

public class ConsultaNaoEncontradaException extends RuntimeException {
    private static final String defaultMessage = "consulta não encontrado";

    public ConsultaNaoEncontradaException() {
        super(defaultMessage);
    }

    public ConsultaNaoEncontradaException(String message) {
        super(message);
    }
}
