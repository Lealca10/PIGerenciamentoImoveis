package com.gerenciamento.imoveis.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OcorrenciaDTO {

    private String id;
    private String imovelId;
    private String descricao;
    private ImovelDTO imovel;
}
