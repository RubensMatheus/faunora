package br.com.faunora.domain.enums;

public enum UserTipo {
    TUTOR,
    VETERINARIO;

    @Override
    public String toString() { return name().toLowerCase(); }
}
