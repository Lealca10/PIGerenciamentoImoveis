package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Imovel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ImovelDTO {

    private String id;
    private String codigo;
    private String descricao;
    private BigDecimal valor;
    private BigDecimal area;
    private String status;
    
    // Dados do endereço
    private String enderecoId;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    
    // Relações
    private String etapaAtualId;
    private String responsavelId;

    public ImovelDTO(Imovel imovel) {
        this.id = imovel.getId();
        this.codigo = imovel.getCodigo();
        this.descricao = imovel.getDescricao();
        this.valor = imovel.getValor();
        this.area = imovel.getArea();
        this.status = imovel.getStatus();
        
        if (imovel.getEndereco() != null) {
            this.enderecoId = imovel.getEndereco().getId();
            this.numero = imovel.getEndereco().getNumero();
            this.complemento = imovel.getEndereco().getComplemento();
            
            if (imovel.getEndereco().getRua() != null) {
                this.rua = imovel.getEndereco().getRua().getNome();
                this.cep = imovel.getEndereco().getRua().getCep();
                
                if (imovel.getEndereco().getRua().getCidade() != null) {
                    this.cidade = imovel.getEndereco().getRua().getCidade().getNome();
                    
                    if (imovel.getEndereco().getRua().getCidade().getEstado() != null) {
                        this.estado = imovel.getEndereco().getRua().getCidade().getEstado().getSigla();
                    }
                }
            }
        }
        
        this.etapaAtualId = imovel.getEtapaAtual() != null ? imovel.getEtapaAtual().getId() : null;
        this.responsavelId = imovel.getResponsavel() != null ? imovel.getResponsavel().getId().toString() : null;
    }
}