package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Rua;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RuaDTO {
    
    private String id;
    private String nome;
    private String cep;
    private String cidadeId;
    private String cidadeNome;
    private String estadoSigla;

    public RuaDTO(Rua rua) {
        this.id = rua.getId();
        this.nome = rua.getNome();
        this.cep = rua.getCep();
        
        if (rua.getCidade() != null) {
            this.cidadeId = rua.getCidade().getId();
            this.cidadeNome = rua.getCidade().getNome();
            
            if (rua.getCidade().getEstado() != null) {
                this.estadoSigla = rua.getCidade().getEstado().getSigla();
            }
        }
    }

    public RuaDTO(String nome, String cep, String cidadeId) {
        this.nome = nome;
        this.cep = cep;
        this.cidadeId = cidadeId;
    }
}
