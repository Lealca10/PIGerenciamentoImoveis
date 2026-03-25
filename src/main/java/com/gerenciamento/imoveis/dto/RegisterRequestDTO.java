package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.TipoUsuario;

public record RegisterRequestDTO(
    String nome,
    String email,
    String senha,
    TipoUsuario tipoUsuario
) {}
