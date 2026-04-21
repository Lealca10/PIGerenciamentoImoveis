package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.dto.ImovelDTO;
import com.gerenciamento.imoveis.entity.*;
import com.gerenciamento.imoveis.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImovelService {

    private final ImovelRepository imovelRepository;
    private final EnderecoService enderecoService;
    private final RuaService ruaService;
    private final CidadeService cidadeService;
    private final EstadoService estadoService;

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

    /**
     * Converte uma ImovelDTO para uma entidade Imovel
     * Gerencia a criação/atualização de Estado, Cidade, Rua e Endereco
     * 
     * @param dto o DTO com os dados do imóvel
     * @return a entidade Imovel configurada
     */
    public Imovel dtoToEntity(ImovelDTO dto) {
        Imovel imovel = new Imovel();
        imovel.setId(dto.getId());
        imovel.setCodigo(dto.getCodigo());
        imovel.setDescricao(dto.getDescricao());
        imovel.setValor(dto.getValor());
        imovel.setArea(dto.getArea());
        imovel.setStatus(dto.getStatus());
        
        // Se já existe um endereço, apenas buscar e atualizar
        if (dto.getEnderecoId() != null && !dto.getEnderecoId().isEmpty()) {
            Endereco endereco = enderecoService.findById(dto.getEnderecoId())
                    .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
            imovel.setEndereco(endereco);
        } else if (dto.getRua() != null && !dto.getRua().isEmpty() && 
                   dto.getEstado() != null && !dto.getEstado().isEmpty() &&
                   dto.getCidade() != null && !dto.getCidade().isEmpty()) {
            // Criar novo endereço a partir dos dados do DTO
            Estado estado = estadoService.findBySigla(dto.getEstado())
                    .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
            
            Cidade cidade = cidadeService.findByNomeAndEstado(dto.getCidade(), estado)
                    .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
            
            Rua rua = ruaService.findByNomeAndCepAndCidade(dto.getRua(), dto.getCep(), cidade)
                    .orElseThrow(() -> new RuntimeException("Rua não encontrada"));
            
            Endereco endereco = new Endereco(rua, dto.getNumero(), dto.getComplemento());
            Endereco enderecoSalvo = enderecoService.save(endereco);
            imovel.setEndereco(enderecoSalvo);
        }
        
        return imovel;
    }
}