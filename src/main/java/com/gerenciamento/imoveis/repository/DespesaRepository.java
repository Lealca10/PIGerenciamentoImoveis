package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, String> {
}