package br.com.faunora.infra.exceptions;

public class NenhumUsuarioEncontradoException extends RuntimeException {
    public NenhumUsuarioEncontradoException() {
        super("Nenhum usuário encontrado.");
    }
}
