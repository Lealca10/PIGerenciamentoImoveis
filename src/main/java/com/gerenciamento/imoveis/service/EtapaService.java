package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Etapa;
import com.gerenciamento.imoveis.repository.EtapaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtapaService {

    private final EtapaRepository etapaRepository;

    public List<Etapa> findAll() {
        return etapaRepository.findAll();
    }

    public Optional<Etapa> findById(String id) {
        return etapaRepository.findById(id);
    }

    public Etapa save(Etapa etapa) {
        return etapaRepository.save(etapa);
    }

    public void deleteById(String id) {
        etapaRepository.deleteById(id);
    }
}