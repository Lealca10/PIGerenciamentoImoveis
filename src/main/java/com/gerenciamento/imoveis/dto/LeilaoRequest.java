package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.LeilaoStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoRequest {

    private String imovelId;
    private Integer numero;
    private BigDecimal valorMinimo;
    private LocalDate data;
    private LeilaoStatus status;

    public String getImovelId() {
        return imovelId;
    }

    public void setImovelId(String imovelId) {
        this.imovelId = imovelId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(BigDecimal valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LeilaoStatus getStatus() {
        return status;
    }

    public void setStatus(LeilaoStatus status) {
        this.status = status;
    }
}