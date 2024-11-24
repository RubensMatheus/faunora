package br.com.faunora.domain.enums;

public enum PetSexo {
    FEMININO,
    MASCULINO;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
