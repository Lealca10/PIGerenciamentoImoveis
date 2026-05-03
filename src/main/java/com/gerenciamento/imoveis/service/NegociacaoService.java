package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.dto.NegociacaoDTO;
import com.gerenciamento.imoveis.entity.Negociacao;
import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.entity.Cliente;
import com.gerenciamento.imoveis.repository.NegociacaoRepository;
import com.gerenciamento.imoveis.repository.ImovelRepository;
import com.gerenciamento.imoveis.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NegociacaoService {

    private final NegociacaoRepository repository;
    private final ImovelRepository imovelRepository;
    private final ClienteRepository clienteRepository;

    public List<NegociacaoDTO> listar() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NegociacaoDTO obterPorId(String id) {
        Negociacao negociacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Negociação não encontrada"));
        return convertToDTO(negociacao);
    }

    public List<NegociacaoDTO> obterPorImovel(String imovelId) {
        return repository.findByImovelId(imovelId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<NegociacaoDTO> obterPorCliente(String clienteId) {
        return repository.findByClienteId(clienteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NegociacaoDTO criar(NegociacaoDTO dto) {
        Imovel imovel = imovelRepository.findById(dto.getImovelId())
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
        
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Negociacao negociacao = new Negociacao();
        negociacao.setImovel(imovel);
        negociacao.setCliente(cliente);
        negociacao.setValor(dto.getValor());
        negociacao.setAmigavel(dto.getAmigavel());

        Negociacao saved = repository.save(negociacao);
        return convertToDTO(saved);
    }

    public NegociacaoDTO atualizar(String id, NegociacaoDTO dto) {
        Negociacao negociacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Negociação não encontrada"));

        if (dto.getValor() != null) {
            negociacao.setValor(dto.getValor());
        }
        
        if (dto.getAmigavel() != null) {
            negociacao.setAmigavel(dto.getAmigavel());
        }

        if (dto.getImovelId() != null && !dto.getImovelId().equals(negociacao.getImovel().getId())) {
            Imovel imovel = imovelRepository.findById(dto.getImovelId())
                    .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
            negociacao.setImovel(imovel);
        }

        if (dto.getClienteId() != null && !dto.getClienteId().equals(negociacao.getCliente().getId())) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            negociacao.setCliente(cliente);
        }

        Negociacao updated = repository.save(negociacao);
        return convertToDTO(updated);
    }

    public void deletar(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Negociação não encontrada");
        }
        repository.deleteById(id);
    }

    private NegociacaoDTO convertToDTO(Negociacao negociacao) {
        NegociacaoDTO dto = new NegociacaoDTO();
        dto.setId(negociacao.getId());
        dto.setImovelId(negociacao.getImovel().getId());
        dto.setClienteId(negociacao.getCliente().getId());
        dto.setValor(negociacao.getValor());
        dto.setAmigavel(negociacao.getAmigavel());
        return dto;
    }
}
