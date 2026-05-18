package com.gerenciamento.imoveis.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DespesaCriarDTO {

    private String imovelId;
    private String categoria;
    private BigDecimal valor;
    private LocalDate data;
    private Boolean aprovado;
}
