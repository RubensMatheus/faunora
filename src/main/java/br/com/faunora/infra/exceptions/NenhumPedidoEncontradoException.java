package br.com.faunora.infra.exceptions;

public class NenhumPedidoEncontradoException extends RuntimeException {
    private static final String defaultMessage = "nenhum pedido encontrado";

    public NenhumPedidoEncontradoException() {
        super(defaultMessage);
    }

    public NenhumPedidoEncontradoException(String message) {
        super(message);
    }
}
