package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.EtapaEnum;
import lombok.Data;

@Data
public class ImovelEtapaStatusRequest {
    private EtapaEnum etapa;
    private String status;
}
