package com.gerenciamento.imoveis.dto;

import java.util.List;

public class HistoricoDuracoesResponseDto {
    private List<HistoricoDuracaoDto> duracoes;
    private Double totalDays;

    public HistoricoDuracoesResponseDto() {}

    public HistoricoDuracoesResponseDto(List<HistoricoDuracaoDto> duracoes, Double totalDays) {
        this.duracoes = duracoes;
        this.totalDays = totalDays;
    }

    public List<HistoricoDuracaoDto> getDuracoes() { return duracoes; }
    public void setDuracoes(List<HistoricoDuracaoDto> duracoes) { this.duracoes = duracoes; }

    public Double getTotalDays() { return totalDays; }
    public void setTotalDays(Double totalDays) { this.totalDays = totalDays; }
}
