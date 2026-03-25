package com.gerenciamento.imoveis.dto;

import java.util.UUID;

import com.gerenciamento.imoveis.entity.TipoUsuario;

public record UsuarioDTO(
        UUID id,
        String nome,
        String email,
        TipoUsuario tipoUsuario
) {}