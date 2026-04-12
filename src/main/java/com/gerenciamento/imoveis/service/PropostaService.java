package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Proposta;
import com.gerenciamento.imoveis.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository propostaRepository;

    public List<Proposta> findAll() {
        return propostaRepository.findAll();
    }

    public Optional<Proposta> findById(String id) {
        return propostaRepository.findById(id);
    }

    public Proposta save(Proposta proposta) {
        return propostaRepository.save(proposta);
    }

    public void deleteById(String id) {
        propostaRepository.deleteById(id);
    }
}