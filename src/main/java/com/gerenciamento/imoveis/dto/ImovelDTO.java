package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Imovel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ImovelDTO {

    private String id;
    private String codigo;
    private String endereco;
    private String cidade;
    private String estado;
    private BigDecimal valor;
    private BigDecimal area;
    private String etapaAtualId;
    private String responsavelId;
    private String status;

    public ImovelDTO(Imovel imovel) {
        this.id = imovel.getId();
        this.codigo = imovel.getCodigo();
        this.endereco = imovel.getEndereco();
        this.cidade = imovel.getCidade();
        this.estado = imovel.getEstado();
        this.valor = imovel.getValor();
        this.area = imovel.getArea();
        this.etapaAtualId = imovel.getEtapaAtual() != null ? imovel.getEtapaAtual().getId() : null;
        this.responsavelId = imovel.getResponsavel() != null ? imovel.getResponsavel().getId().toString() : null;
        this.status = imovel.getStatus();
    }
}