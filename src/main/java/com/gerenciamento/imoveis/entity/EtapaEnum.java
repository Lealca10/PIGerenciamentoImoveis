package com.gerenciamento.imoveis.entity;

public enum EtapaEnum {
    CADASTRO("Cadastro"),
    LEILAO("Leilão"),
    NEGOCIACAO_AMIGAVEL("Negociação Amigável"),
    NEGOCIACAO_NAO_AMIGAVEL("Negociação Não Amigável"),
    JURIDICO("Jurídico"),
    COMERCIAL("Comercial"),
    VENDA("Venda"),
    POS_VENDA("Pós Venda"),
    MANUTENCAO_PRECIFICACAO("Manutenção/Precificação");

    private final String descricao;

    EtapaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
