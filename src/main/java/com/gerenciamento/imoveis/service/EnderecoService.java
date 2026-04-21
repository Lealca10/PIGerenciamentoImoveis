package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Endereco;
import com.gerenciamento.imoveis.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(String id) {
        return enderecoRepository.findById(id);
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void deleteById(String id) {
        enderecoRepository.deleteById(id);
    }
}
