package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Cidade;
import com.gerenciamento.imoveis.entity.Estado;
import com.gerenciamento.imoveis.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> findById(String id) {
        return cidadeRepository.findById(id);
    }

    public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado) {
        return cidadeRepository.findByNomeAndEstado(nome, estado);
    }

    public List<Cidade> findByEstado(Estado estado) {
        return cidadeRepository.findByEstado(estado);
    }

    public Cidade save(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void deleteById(String id) {
        cidadeRepository.deleteById(id);
    }
}
