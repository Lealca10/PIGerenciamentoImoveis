package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Estado;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstadoDTO {
    
    private String id;
    private String sigla;
    private String nome;

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.sigla = estado.getSigla();
        this.nome = estado.getNome();
    }

    public EstadoDTO(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }
}
