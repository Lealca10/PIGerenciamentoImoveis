package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, String> {
}