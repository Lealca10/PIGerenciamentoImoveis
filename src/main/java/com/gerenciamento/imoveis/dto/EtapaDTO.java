package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Etapa;
import lombok.Data;

@Data
public class EtapaDTO {

    private String id;
    private Integer ordem;

    public EtapaDTO(Etapa etapa) {
        this.id = etapa.getId();
        this.ordem = etapa.getOrdem();
    }
}