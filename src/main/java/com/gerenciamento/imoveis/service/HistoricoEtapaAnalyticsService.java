package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.dto.HistoricoDuracaoAggregateDto;
import com.gerenciamento.imoveis.dto.HistoricoDuracaoDto;
import com.gerenciamento.imoveis.repository.HistoricoEtapaQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoEtapaAnalyticsService {

    private final HistoricoEtapaQueryRepository repo;

    public HistoricoEtapaAnalyticsService(HistoricoEtapaQueryRepository repo) {
        this.repo = repo;
    }

    public List<HistoricoDuracaoDto> getDurations(String imovelId) {
        return repo.findDurationsByImovelId(imovelId);
    }

    public List<HistoricoDuracaoAggregateDto> getAggregated(String imovelId) {
        return repo.findAggregatedByImovelId(imovelId);
    }
}
