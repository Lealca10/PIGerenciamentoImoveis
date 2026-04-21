package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Estado;
import com.gerenciamento.imoveis.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> findById(String id) {
        return estadoRepository.findById(id);
    }

    public Optional<Estado> findBySigla(String sigla) {
        return estadoRepository.findBySigla(sigla);
    }

    public Optional<Estado> findByNome(String nome) {
        return estadoRepository.findByNome(nome);
    }

    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void deleteById(String id) {
        estadoRepository.deleteById(id);
    }
}
