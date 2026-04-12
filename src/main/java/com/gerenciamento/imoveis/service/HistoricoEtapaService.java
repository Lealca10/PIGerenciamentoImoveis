package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.HistoricoEtapa;
import com.gerenciamento.imoveis.repository.HistoricoEtapaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoEtapaService {

    private final HistoricoEtapaRepository historicoEtapaRepository;

    public List<HistoricoEtapa> findAll() {
        return historicoEtapaRepository.findAll();
    }

    public Optional<HistoricoEtapa> findById(String id) {
        return historicoEtapaRepository.findById(id);
    }

    public HistoricoEtapa save(HistoricoEtapa historicoEtapa) {
        return historicoEtapaRepository.save(historicoEtapa);
    }

    public void deleteById(String id) {
        historicoEtapaRepository.deleteById(id);
    }
}