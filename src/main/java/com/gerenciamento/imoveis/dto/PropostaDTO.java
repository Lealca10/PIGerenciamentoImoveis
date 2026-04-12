package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Proposta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PropostaDTO {

    private String id;
    private String imovelId;
    private String cliente;
    private BigDecimal valor;
    private String status;
    private LocalDate data;

    public PropostaDTO(Proposta proposta) {
        this.id = proposta.getId();
        this.imovelId = proposta.getImovel() != null ? proposta.getImovel().getId() : null;
        this.cliente = proposta.getCliente();
        this.valor = proposta.getValor();
        this.status = proposta.getStatus();
        this.data = proposta.getData();
    }
}