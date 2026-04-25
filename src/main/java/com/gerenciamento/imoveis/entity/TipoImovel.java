package com.gerenciamento.imoveis.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoImovel {
    APARTAMENTO("Apartamento"),
    CASA("Casa"),
    COMERCIAL_SALA("Comercial/sala"),
    TERRENO("Terreno"),
    GALPAO_ARMAZEM("Galpão/Armazém"),
    PROPRIEDADE_RURAL("Propriedade Rural");

    private final String descricao;

    TipoImovel(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoImovel fromDescricao(String descricao) {
        for (TipoImovel tipo : values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        for (TipoImovel tipo : values()) {
            if (tipo.name().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoImovel inválido: " + descricao);
    }
}
