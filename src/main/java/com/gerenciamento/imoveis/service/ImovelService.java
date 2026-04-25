package com.gerenciamento.imoveis.service;

import com.gerenciamento.imoveis.dto.EnderecoDTO;
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
    private final BairroService bairroService;

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
        imovel.setTipoImovel(dto.getTipoImovel());
        imovel.setArea(dto.getArea());
        imovel.setQuartos(dto.getQuartos());
        imovel.setBanheiros(dto.getBanheiros());
        imovel.setVagasGaragem(dto.getVagasGaragem());
        imovel.setValorAvaliacao(dto.getValorAvaliacao());
        imovel.setDataAvaliacao(dto.getDataAvaliacao());
        imovel.setNumeroMatricula(dto.getNumeroMatricula());
        imovel.setCartorioRegistro(dto.getCartorioRegistro());
        imovel.setDescricao(dto.getDescricao());
        imovel.setFotosImovel(dto.getFotosImovel());

        EnderecoDTO enderecoDTO = dto.getEndereco();
        if (enderecoDTO != null) {
            Rua rua = null;
            if (enderecoDTO.getRuaId() != null && !enderecoDTO.getRuaId().isEmpty()) {
                rua = ruaService.findById(enderecoDTO.getRuaId())
                        .orElseThrow(() -> new RuntimeException("Rua não encontrada"));
            } else if (enderecoDTO.getRuaNome() != null && !enderecoDTO.getRuaNome().isEmpty() &&
                       enderecoDTO.getCep() != null && !enderecoDTO.getCep().isEmpty()) {
                rua = ruaService.findByNomeAndCep(enderecoDTO.getRuaNome(), enderecoDTO.getCep())
                        .orElseGet(() -> ruaService.save(new Rua(enderecoDTO.getRuaNome(), enderecoDTO.getCep())));
            } else {
                throw new RuntimeException("Rua não informada");
            }

            Bairro bairro = null;
            if (enderecoDTO.getBairroId() != null && !enderecoDTO.getBairroId().isEmpty()) {
                bairro = bairroService.findById(enderecoDTO.getBairroId())
                        .orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
            } else if (enderecoDTO.getBairroDescricao() != null && !enderecoDTO.getBairroDescricao().isEmpty()) {
                bairro = bairroService.findByDescricao(enderecoDTO.getBairroDescricao())
                        .orElseGet(() -> bairroService.save(new Bairro(enderecoDTO.getBairroDescricao())));
            } else {
                throw new RuntimeException("Bairro não informado");
            }

            final Estado estadoFinal;
            if (enderecoDTO.getEstadoId() != null && !enderecoDTO.getEstadoId().isEmpty()) {
                estadoFinal = estadoService.findById(enderecoDTO.getEstadoId())
                        .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
            } else if (enderecoDTO.getEstadoSigla() != null && !enderecoDTO.getEstadoSigla().isEmpty()) {
                estadoFinal = estadoService.findBySigla(enderecoDTO.getEstadoSigla())
                        .orElseGet(() -> estadoService.save(new Estado(enderecoDTO.getEstadoSigla(), enderecoDTO.getEstadoSigla())));
            } else {
                throw new RuntimeException("Estado não informado");
            }

            final Cidade cidadeFinal;
            if (enderecoDTO.getCidadeId() != null && !enderecoDTO.getCidadeId().isEmpty()) {
                cidadeFinal = cidadeService.findById(enderecoDTO.getCidadeId())
                        .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
            } else if (enderecoDTO.getCidadeNome() != null && !enderecoDTO.getCidadeNome().isEmpty()) {
                cidadeFinal = cidadeService.findByNomeAndEstado(enderecoDTO.getCidadeNome(), estadoFinal)
                        .orElseGet(() -> cidadeService.save(new Cidade(enderecoDTO.getCidadeNome(), estadoFinal)));
            } else {
                throw new RuntimeException("Cidade não informada");
            }

            Endereco endereco = new Endereco(
                    enderecoDTO.getCep(),
                    rua,
                    bairro,
                    cidadeFinal,
                    estadoFinal,
                    enderecoDTO.getNumero(),
                    enderecoDTO.getComplemento()
            );
            Endereco enderecoSalvo = enderecoService.save(endereco);
            imovel.setEndereco(enderecoSalvo);
        }

        return imovel;
    }
}