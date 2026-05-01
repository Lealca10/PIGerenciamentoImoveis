package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private String id;
    private String nome;
    private Integer conta;
    private Integer agencia;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.conta = cliente.getConta();
        this.agencia = cliente.getAgencia();
    }
}
