package br.com.faunora.domain.enums;

public enum ProdutoTipo {
    HIGIENE,
    ALIMENTAÇÃO,
    LAZER,
    MEDICAÇÃO;

    @Override
    public String toString() { return name().toLowerCase(); }
}
