package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {
    List<Documento> findByImovelId(String imovelId);
}
