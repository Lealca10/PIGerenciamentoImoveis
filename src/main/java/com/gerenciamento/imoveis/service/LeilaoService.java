package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Leilao;
import com.gerenciamento.imoveis.repository.LeilaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;

    public List<Leilao> findAll() {
        return leilaoRepository.findAll();
    }

    public Optional<Leilao> findById(String id) {
        return leilaoRepository.findById(id);
    }

    public Leilao save(Leilao leilao) {
        return leilaoRepository.save(leilao);
    }

    public void deleteById(String id) {
        leilaoRepository.deleteById(id);
    }
}