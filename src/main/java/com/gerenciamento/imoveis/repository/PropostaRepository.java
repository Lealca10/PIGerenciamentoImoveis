package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, String> {
}