package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Cidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDTO {
    
    private String id;
    private String nome;
    private String estadoId;
    private String estadoNome;
    private String estadoSigla;

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        
        if (cidade.getEstado() != null) {
            this.estadoId = cidade.getEstado().getId();
            this.estadoNome = cidade.getEstado().getNome();
            this.estadoSigla = cidade.getEstado().getSigla();
        }
    }

    public CidadeDTO(String nome, String estadoId) {
        this.nome = nome;
        this.estadoId = estadoId;
    }
}
