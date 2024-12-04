package br.com.faunora.infra.exceptions.datas;

public class DataIndisponivelException extends RuntimeException {
    private static final String defaultMessage = "data indisponível para agendamento";

    public DataIndisponivelException() {
        super(defaultMessage);
    }

    public DataIndisponivelException(String message) {
        super(message);
    }
}
