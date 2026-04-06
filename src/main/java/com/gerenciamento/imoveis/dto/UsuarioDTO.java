package com.gerenciamento.imoveis.dto;

import java.util.UUID;

import com.gerenciamento.imoveis.entity.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {

    private UUID id;
    private String nome;
    private String email;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }
}