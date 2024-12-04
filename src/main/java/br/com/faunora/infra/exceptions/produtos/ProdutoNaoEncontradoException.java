package br.com.faunora.infra.exceptions.produtos;

public class ProdutoNaoEncontradoException extends RuntimeException {
    private static final String defaultMessage = "produto não encontrado";

    public ProdutoNaoEncontradoException() {
        super(defaultMessage);
    }

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
