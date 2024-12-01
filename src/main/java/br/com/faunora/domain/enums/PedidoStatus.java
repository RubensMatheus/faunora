package br.com.faunora.domain.enums;

public enum PedidoStatus {
    ENTREGUE,
    EXPIRADO,
    CANCELADO,
    PENDENTE;

    @Override
    public String toString() { return name().toLowerCase(); }
}
