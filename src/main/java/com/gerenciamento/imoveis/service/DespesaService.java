package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Despesa;
import com.gerenciamento.imoveis.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public List<Despesa> findAll() {
        return despesaRepository.findAll();
    }

    public Optional<Despesa> findById(String id) {
        return despesaRepository.findById(id);
    }

    public Despesa save(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public void deleteById(String id) {
        despesaRepository.deleteById(id);
    }
}