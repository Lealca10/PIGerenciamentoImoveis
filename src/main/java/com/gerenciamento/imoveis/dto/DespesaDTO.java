package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Despesa;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DespesaDTO {

    private String id;
    private String imovelId;
    private String categoria;
    private BigDecimal valor;
    private LocalDate data;
    private Boolean aprovado;

    public DespesaDTO(Despesa despesa) {
        this.id = despesa.getId();
        this.imovelId = despesa.getImovel() != null ? despesa.getImovel().getId() : null;
        this.categoria = despesa.getCategoria();
        this.valor = despesa.getValor();
        this.data = despesa.getData();
        this.aprovado = despesa.getAprovado();
    }
}