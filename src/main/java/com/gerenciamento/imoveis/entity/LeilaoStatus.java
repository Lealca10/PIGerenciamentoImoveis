package com.gerenciamento.imoveis.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LeilaoStatus {
    PRIMEIRO_LEILAO("1º LEILÃO"),
    SEGUNDO_LEILAO("2º LEILÃO"),
    COMERCIALIZACAO("COMERCIALIZAÇÃO");

    private final String label;

    LeilaoStatus(String label) {
        this.label = label;
    }

    @JsonValue
    @Override
    public String toString() {
        return label;
    }

    @JsonCreator
    public static LeilaoStatus fromValue(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.trim();
        for (LeilaoStatus status : values()) {
            if (status.label.equalsIgnoreCase(normalized) || status.name().equalsIgnoreCase(normalized)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de leilão inválido: " + value);
    }

    public String getLabel() {
        return label;
    }
}
