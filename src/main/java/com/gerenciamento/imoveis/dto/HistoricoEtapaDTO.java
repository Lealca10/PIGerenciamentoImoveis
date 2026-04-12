package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.HistoricoEtapa;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoricoEtapaDTO {

    private String id;
    private String imovelId;
    private String etapaDe;
    private String etapaPara;
    private String usuarioId;
    private LocalDateTime data;

    public HistoricoEtapaDTO(HistoricoEtapa historicoEtapa) {
        this.id = historicoEtapa.getId();
        this.imovelId = historicoEtapa.getImovel() != null ? historicoEtapa.getImovel().getId() : null;
        this.etapaDe = historicoEtapa.getEtapaDe();
        this.etapaPara = historicoEtapa.getEtapaPara();
        this.usuarioId = historicoEtapa.getUsuario() != null ? historicoEtapa.getUsuario().getId().toString() : null;
        this.data = historicoEtapa.getData();
    }
}