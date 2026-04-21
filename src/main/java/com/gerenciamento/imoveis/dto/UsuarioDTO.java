package com.gerenciamento.imoveis.dto;

import java.util.UUID;

import com.gerenciamento.imoveis.entity.TipoUsuario;
import com.gerenciamento.imoveis.entity.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO {

    private UUID id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.tipoUsuario = usuario.getTipoUsuario();
    }
}