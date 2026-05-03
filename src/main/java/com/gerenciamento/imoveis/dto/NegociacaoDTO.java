package com.gerenciamento.imoveis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class NegociacaoDTO {

    private String id;
    private String imovelId;
    private String clienteId;
    private BigDecimal valor;
    private Boolean amigavel;
    private ImovelDTO imovel;
    private ClienteDTO cliente;
}
