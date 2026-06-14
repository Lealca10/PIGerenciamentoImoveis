package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.dto.HistoricoDuracaoAggregateDto;
import com.gerenciamento.imoveis.dto.HistoricoDuracaoDto;
import com.gerenciamento.imoveis.repository.HistoricoEtapaQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import com.gerenciamento.imoveis.dto.HistoricoDuracoesResponseDto;

@Service
public class HistoricoEtapaAnalyticsService {

    private final HistoricoEtapaQueryRepository repo;

    public HistoricoEtapaAnalyticsService(HistoricoEtapaQueryRepository repo) {
        this.repo = repo;
    }

    public List<HistoricoDuracaoDto> getDurations(String imovelId) {
        return repo.findDurationsByImovelId(imovelId);
    }

    public HistoricoDuracoesResponseDto getDurationsWithTotal(String imovelId) {
        List<HistoricoDuracaoDto> list = repo.findDurationsByImovelId(imovelId);
        double total = 0.0;
        for (HistoricoDuracaoDto d : list) {
            if (d != null && d.getDays() != null) total += d.getDays();
        }
        return new HistoricoDuracoesResponseDto(list, total);
    }

    public List<HistoricoDuracaoAggregateDto> getAggregated(String imovelId) {
        return repo.findAggregatedByImovelId(imovelId);
    }
}
