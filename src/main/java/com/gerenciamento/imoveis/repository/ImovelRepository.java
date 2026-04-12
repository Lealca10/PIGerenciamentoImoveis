package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, String> {
}