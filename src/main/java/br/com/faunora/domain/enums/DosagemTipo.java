package br.com.faunora.domain.enums;

public enum DosagemTipo {
    QUINTUPLA,
    ANTIRRABICA,
    GIARDIASE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
