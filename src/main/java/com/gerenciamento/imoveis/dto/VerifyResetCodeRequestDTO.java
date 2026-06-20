package com.gerenciamento.imoveis.dto;

public record VerifyResetCodeRequestDTO(
    String email,
    String codigo
) {}