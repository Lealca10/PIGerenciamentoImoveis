package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Rua;
import com.gerenciamento.imoveis.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuaRepository extends JpaRepository<Rua, String> {
    Optional<Rua> findByNomeAndCepAndCidade(String nome, String cep, Cidade cidade);
    List<Rua> findByCidade(Cidade cidade);
}
