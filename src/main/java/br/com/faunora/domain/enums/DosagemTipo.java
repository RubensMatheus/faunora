package br.com.faunora.domain.enums;

public enum DosagemTipo {
    QUÍNTUPLA,
    ANTIRRÁBICA,
    GIARDÍASE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
