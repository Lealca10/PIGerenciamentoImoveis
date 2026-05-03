package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.dto.OcorrenciaDTO;
import com.gerenciamento.imoveis.entity.Ocorrencia;
import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.repository.OcorrenciaRepository;
import com.gerenciamento.imoveis.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository repository;
    private final ImovelRepository imovelRepository;

    public List<OcorrenciaDTO> listar() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OcorrenciaDTO obterPorId(String id) {
        Ocorrencia ocorrencia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
        return convertToDTO(ocorrencia);
    }

    public List<OcorrenciaDTO> obterPorImovel(String imovelId) {
        return repository.findByImovelId(imovelId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OcorrenciaDTO criar(OcorrenciaDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setImovel(imovel);
        ocorrencia.setDescricao(dto.getDescricao());

        Ocorrencia saved = repository.save(ocorrencia);
        return convertToDTO(saved);
    }

    public OcorrenciaDTO atualizar(String id, OcorrenciaDTO dto) {
        Ocorrencia ocorrencia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        if (dto.getDescricao() != null) {
            ocorrencia.setDescricao(dto.getDescricao());
        }

        if (dto.getImovelId() != null && !dto.getImovelId().equals(ocorrencia.getImovel().getId())) {
            Imovel imovel = imovelRepository.findById(dto.getImovelId())
                    .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
            ocorrencia.setImovel(imovel);
        }

        Ocorrencia updated = repository.save(ocorrencia);
        return convertToDTO(updated);
    }

    public void deletar(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ocorrência não encontrada");
        }
        repository.deleteById(id);
    }

    private OcorrenciaDTO convertToDTO(Ocorrencia ocorrencia) {
        OcorrenciaDTO dto = new OcorrenciaDTO();
        dto.setId(ocorrencia.getId());
        dto.setImovelId(ocorrencia.getImovel().getId());
        dto.setDescricao(ocorrencia.getDescricao());
        return dto;
    }
}
