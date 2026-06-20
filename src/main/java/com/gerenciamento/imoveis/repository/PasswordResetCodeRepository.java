package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, UUID> {
    Optional<PasswordResetCode> findFirstByEmailAndCodigoAndUsadoFalseOrderByCriadoEmDesc(String email, String codigo);
}