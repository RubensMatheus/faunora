package br.com.faunora.infra.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
    private static final String defaultMessage = "pedido não encontrado";

    public PedidoNaoEncontradoException() {
        super(defaultMessage);
    }

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
