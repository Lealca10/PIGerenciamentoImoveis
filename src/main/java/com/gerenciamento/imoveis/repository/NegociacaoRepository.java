package com.gerenciamento.imoveis.repository;

import com.gerenciamento.imoveis.entity.Negociacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NegociacaoRepository extends JpaRepository<Negociacao, String> {
    List<Negociacao> findByImovelId(String imovelId);
    List<Negociacao> findByClienteId(String clienteId);
}
