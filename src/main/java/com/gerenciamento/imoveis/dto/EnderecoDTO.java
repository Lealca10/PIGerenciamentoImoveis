package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDTO {
    
    private String id;
    private String numero;
    private String complemento;
    private String ruaId;
    private String ruaNome;
    private String cep;
    private String cidadeNome;
    private String estadoSigla;

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        
        if (endereco.getRua() != null) {
            this.ruaId = endereco.getRua().getId();
            this.ruaNome = endereco.getRua().getNome();
            this.cep = endereco.getRua().getCep();
            
            if (endereco.getRua().getCidade() != null) {
                this.cidadeNome = endereco.getRua().getCidade().getNome();
                
                if (endereco.getRua().getCidade().getEstado() != null) {
                    this.estadoSigla = endereco.getRua().getCidade().getEstado().getSigla();
                }
            }
        }
    }

    public EnderecoDTO(String numero, String complemento, String ruaId) {
        this.numero = numero;
        this.complemento = complemento;
        this.ruaId = ruaId;
    }
}
