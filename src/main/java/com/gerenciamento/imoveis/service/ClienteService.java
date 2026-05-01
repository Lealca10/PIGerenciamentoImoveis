package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Cliente;
import com.gerenciamento.imoveis.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Cliente> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
