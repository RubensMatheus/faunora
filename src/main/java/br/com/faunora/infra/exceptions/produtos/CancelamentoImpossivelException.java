package br.com.faunora.infra.exceptions.produtos;

public class CancelamentoImpossivelException extends RuntimeException {
    private static final String defaultMessage = "pedido não está mais válido para cancelamento";

    public CancelamentoImpossivelException() {
        super(defaultMessage);
    }

    public CancelamentoImpossivelException(String message) {
        super(message);
    }
}
