package com.gerenciamento.imoveis.dto;

import java.time.LocalDateTime;

public class HistoricoDuracaoDto {
    private String imovelId;
    private String etapa;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Double days;

    public HistoricoDuracaoDto() {}

    public HistoricoDuracaoDto(String imovelId, String etapa, LocalDateTime startedAt, LocalDateTime endedAt, Double days) {
        this.imovelId = imovelId;
        this.etapa = etapa;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.days = days;
    }

    public String getImovelId() { return imovelId; }
    public void setImovelId(String imovelId) { this.imovelId = imovelId; }

    public String getEtapa() { return etapa; }
    public void setEtapa(String etapa) { this.etapa = etapa; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }

    public Double getDays() { return days; }
    public void setDays(Double days) { this.days = days; }
}
