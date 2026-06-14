package com.gerenciamento.imoveis.dto;

public class HistoricoDuracaoAggregateDto {
    private String imovelId;
    private String etapa;
    private Double totalDays;

    public HistoricoDuracaoAggregateDto() {}

    public HistoricoDuracaoAggregateDto(String imovelId, String etapa, Double totalDays) {
        this.imovelId = imovelId;
        this.etapa = etapa;
        this.totalDays = totalDays;
    }

    public String getImovelId() { return imovelId; }
    public void setImovelId(String imovelId) { this.imovelId = imovelId; }

    public String getEtapa() { return etapa; }
    public void setEtapa(String etapa) { this.etapa = etapa; }

    public Double getTotalDays() { return totalDays; }
    public void setTotalDays(Double totalDays) { this.totalDays = totalDays; }
}
