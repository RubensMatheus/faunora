package br.com.faunora.infra.exceptions.users;

public class NenhumVeterinarioDisponivelException extends RuntimeException {
    private static final String defaultMessage = "nenhum veterinário disponível para dia e horário fornecidos";

    public NenhumVeterinarioDisponivelException() {
        super(defaultMessage);
    }

    public NenhumVeterinarioDisponivelException(String message) {
        super(message);
    }
}
