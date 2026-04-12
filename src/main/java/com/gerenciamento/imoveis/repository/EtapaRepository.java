package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, String> {
}