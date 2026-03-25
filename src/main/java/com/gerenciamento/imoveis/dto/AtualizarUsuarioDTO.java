package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.TipoUsuario;

public record AtualizarUsuarioDTO(
        String nome,
        String senha,
        TipoUsuario tipoUsuario
) {}