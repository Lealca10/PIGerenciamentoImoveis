package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Rua;
import com.gerenciamento.imoveis.entity.Cidade;
import com.gerenciamento.imoveis.repository.RuaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuaService {

    private final RuaRepository ruaRepository;

    public List<Rua> findAll() {
        return ruaRepository.findAll();
    }

    public Optional<Rua> findById(String id) {
        return ruaRepository.findById(id);
    }

    public Optional<Rua> findByNomeAndCepAndCidade(String nome, String cep, Cidade cidade) {
        return ruaRepository.findByNomeAndCepAndCidade(nome, cep, cidade);
    }

    public List<Rua> findByCidade(Cidade cidade) {
        return ruaRepository.findByCidade(cidade);
    }

    public Rua save(Rua rua) {
        return ruaRepository.save(rua);
    }

    public void deleteById(String id) {
        ruaRepository.deleteById(id);
    }
}
