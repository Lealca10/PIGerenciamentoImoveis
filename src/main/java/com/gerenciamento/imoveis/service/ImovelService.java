package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImovelService {

    private final ImovelRepository imovelRepository;

    public List<Imovel> findAll() {
        return imovelRepository.findAll();
    }

    public Optional<Imovel> findById(String id) {
        return imovelRepository.findById(id);
    }

    public Imovel save(Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    public void deleteById(String id) {
        imovelRepository.deleteById(id);
    }
}