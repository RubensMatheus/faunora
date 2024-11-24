package br.com.faunora.domain.enums;

public enum PetTipo {
    CANINO,
    FELINO;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
