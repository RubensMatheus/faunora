package br.com.faunora.infra.exceptions;

public class HorarioIndisponivelException extends RuntimeException {
    private static final String defaultMessage = "horário indisponível para agendamento";

    public HorarioIndisponivelException() {
        super(defaultMessage);
    }

    public HorarioIndisponivelException(String message) {
        super(message);
    }
}
