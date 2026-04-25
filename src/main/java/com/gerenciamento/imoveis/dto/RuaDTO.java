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

    public RuaDTO(Rua rua) {
        this.id = rua.getId();
        this.nome = rua.getNome();
        this.cep = rua.getCep();
    }

    public RuaDTO(String nome, String cep) {
        this.nome = nome;
        this.cep = cep;
    }
}
