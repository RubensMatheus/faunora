package br.com.faunora.domain.enums;

public enum ExameTipo {
    HEMOGRAMA,
    ULTRASSOM,
    RAIOX,
    ENDOSCOPIA;

    @Override
    public String toString() {
        return this == ExameTipo.RAIOX ? "raio-x" : name().toLowerCase();
    }
}
