package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Leilao;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LeilaoDTO {

    private String id;
    private String imovelId;
    private Integer numero;
    private LocalDate data;
    private BigDecimal valorMinimo;
    private String status;

    public LeilaoDTO(Leilao leilao) {
        this.id = leilao.getId();
        this.imovelId = leilao.getImovel() != null ? leilao.getImovel().getId() : null;
        this.numero = leilao.getNumero();
        this.data = leilao.getData();
        this.valorMinimo = leilao.getValorMinimo();
        this.status = leilao.getStatus();
    }
}