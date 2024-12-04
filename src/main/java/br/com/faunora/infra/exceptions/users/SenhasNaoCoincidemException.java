package br.com.faunora.infra.exceptions.users;

public class SenhasNaoCoincidemException extends RuntimeException {
    private static final String defaultMessage = "senhas enviadas não coincidem";

    public SenhasNaoCoincidemException() { super(defaultMessage); }

    public SenhasNaoCoincidemException(String mensagem) { super(mensagem); }
}
