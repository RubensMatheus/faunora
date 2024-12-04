package br.com.faunora.domain.enums;

public enum ProdutoTipo {
    HIGIENE,
    ALIMENTACAO,
    LAZER,
    MEDICACAO;

    @Override
    public String toString() { return name().toLowerCase(); }
}
