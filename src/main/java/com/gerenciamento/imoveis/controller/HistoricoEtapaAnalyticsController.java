package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.HistoricoDuracaoAggregateDto;
import com.gerenciamento.imoveis.dto.HistoricoDuracaoDto;
import com.gerenciamento.imoveis.dto.HistoricoDuracoesResponseDto;
import com.gerenciamento.imoveis.service.HistoricoEtapaAnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imoveis")
public class HistoricoEtapaAnalyticsController {

    private final HistoricoEtapaAnalyticsService service;

    public HistoricoEtapaAnalyticsController(HistoricoEtapaAnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/{imovelId}/historico/duracoes")
    public ResponseEntity<HistoricoDuracoesResponseDto> getDurations(@PathVariable String imovelId) {
        return ResponseEntity.ok(service.getDurationsWithTotal(imovelId));
    }

    @GetMapping("/{imovelId}/historico/duracoes/aggregate")
    public ResponseEntity<List<HistoricoDuracaoAggregateDto>> getAggregated(@PathVariable String imovelId) {
        return ResponseEntity.ok(service.getAggregated(imovelId));
    }
}
