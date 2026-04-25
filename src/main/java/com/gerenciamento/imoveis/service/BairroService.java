package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Bairro;
import com.gerenciamento.imoveis.repository.BairroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;

    public List<Bairro> findAll() {
        return bairroRepository.findAll();
    }

    public Optional<Bairro> findById(String id) {
        return bairroRepository.findById(id);
    }

    public Optional<Bairro> findByDescricao(String descricao) {
        return bairroRepository.findByDescricao(descricao);
    }

    public Bairro save(Bairro bairro) {
        return bairroRepository.save(bairro);
    }

    public void deleteById(String id) {
        bairroRepository.deleteById(id);
    }
}
