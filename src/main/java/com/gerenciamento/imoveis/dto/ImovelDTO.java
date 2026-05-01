package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.entity.TipoImovel;
import com.gerenciamento.imoveis.entity.EtapaEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ImovelDTO {

    private String id;
    private TipoImovel tipoImovel;
    private Integer area;
    private Integer quartos;
    private Integer banheiros;
    private Integer vagasGaragem;
    private BigDecimal valorAvaliacao;
    private LocalDate dataAvaliacao;
    private Integer numeroMatricula;
    private String cartorioRegistro;
    private String descricao;
    private List<String> fotosImovel = new ArrayList<>();
    private EnderecoDTO endereco;
    private EtapaEnum etapa;
    private String responsavelId;
    private String clienteId;
    private String status;
    private Object enderecoDTO;

    public ImovelDTO(Imovel imovel) {
        this.id = imovel.getId();
        this.tipoImovel = imovel.getTipoImovel();
        this.area = imovel.getArea();
        this.quartos = imovel.getQuartos();
        this.banheiros = imovel.getBanheiros();
        this.vagasGaragem = imovel.getVagasGaragem();
        this.valorAvaliacao = imovel.getValorAvaliacao();
        this.dataAvaliacao = imovel.getDataAvaliacao();
        this.numeroMatricula = imovel.getNumeroMatricula();
        this.cartorioRegistro = imovel.getCartorioRegistro();
        this.descricao = imovel.getDescricao();
        this.fotosImovel = imovel.getFotosImovel();
        this.enderecoDTO = imovel.getEndereco() != null ? new EnderecoDTO(imovel.getEndereco()) : null;
        this.etapa = imovel.getEtapa();
        this.responsavelId = imovel.getResponsavel() != null ? imovel.getResponsavel().getId().toString() : null;
        this.clienteId = imovel.getCliente() != null ? imovel.getCliente().getId() : null;
        this.status = imovel.getStatus();
    }
}