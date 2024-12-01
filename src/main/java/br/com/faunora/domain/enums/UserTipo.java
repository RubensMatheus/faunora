package br.com.faunora.domain.enums;

public enum UserTipo {
    TUTOR,
    VETERINÁRIO;

    @Override
    public String toString() { return name().toLowerCase(); }
}
