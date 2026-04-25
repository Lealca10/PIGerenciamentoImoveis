package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, String> {
    Optional<Bairro> findByDescricao(String descricao);
}
