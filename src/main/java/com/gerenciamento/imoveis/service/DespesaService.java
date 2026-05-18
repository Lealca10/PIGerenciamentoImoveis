package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.dto.DespesaCriarDTO;
import com.gerenciamento.imoveis.dto.DespesaDTO;
import com.gerenciamento.imoveis.entity.Despesa;
import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.repository.DespesaRepository;
import com.gerenciamento.imoveis.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final ImovelRepository imovelRepository;

    public List<Despesa> findAll() {
        return despesaRepository.findAll();
    }

    public Optional<Despesa> findById(String id) {
        return despesaRepository.findById(id);
    }

    public Despesa save(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public List<Despesa> findByImovelId(String imovelId) {
        return despesaRepository.findByImovelId(imovelId);
    }

    public DespesaDTO criar(DespesaCriarDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        Despesa despesa = new Despesa();
        despesa.setImovel(imovel);
        despesa.setCategoria(dto.getCategoria());
        despesa.setValor(dto.getValor());
        despesa.setData(dto.getData());
        despesa.setAprovado(dto.getAprovado());

        Despesa saved = despesaRepository.save(despesa);
        return new DespesaDTO(saved);
    }

    public DespesaDTO atualizar(String id, DespesaCriarDTO dto) {
        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        if (dto.getImovelId() != null && !dto.getImovelId().equals(despesa.getImovel().getId())) {
            Imovel imovel = imovelRepository.findById(dto.getImovelId())
                    .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
            despesa.setImovel(imovel);
        }

        if (dto.getCategoria() != null) {
            despesa.setCategoria(dto.getCategoria());
        }

        if (dto.getValor() != null) {
            despesa.setValor(dto.getValor());
        }

        if (dto.getData() != null) {
            despesa.setData(dto.getData());
        }

        if (dto.getAprovado() != null) {
            despesa.setAprovado(dto.getAprovado());
        }

        Despesa updated = despesaRepository.save(despesa);
        return new DespesaDTO(updated);
    }

    public void deleteById(String id) {
        despesaRepository.deleteById(id);
    }
}