package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.TipoDocumento;

public class DocumentoDTO {
    private TipoDocumento tipo;
    private String nomeArquivo;
    private String url;

    public DocumentoDTO() {}

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
