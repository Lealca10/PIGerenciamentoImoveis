package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, String> {
    List<Ocorrencia> findByImovelId(String imovelId);
}
