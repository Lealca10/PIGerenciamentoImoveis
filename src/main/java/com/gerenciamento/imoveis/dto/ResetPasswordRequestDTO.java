package com.gerenciamento.imoveis.dto;

public record ResetPasswordRequestDTO(
    String email,
    String codigo,
    String novaSenha
) {}