package com.gerenciamento.imoveis.dto;

import com.gerenciamento.imoveis.entity.Endereco;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnderecoDTO {
    
    private String id;
    private String cep;
    private String ruaId;
    private String ruaNome;
    private String numero;
    private String complemento;
    private String bairroId;
    private String bairroDescricao;
    private String cidadeId;
    private String cidadeNome;
    private String estadoId;
    private String estadoSigla;

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.ruaId = endereco.getRua() != null ? endereco.getRua().getId() : null;
        this.ruaNome = endereco.getRua() != null ? endereco.getRua().getNome() : null;
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairroId = endereco.getBairro() != null ? endereco.getBairro().getId() : null;
        this.bairroDescricao = endereco.getBairro() != null ? endereco.getBairro().getDescricao() : null;
        this.cidadeId = endereco.getCidade() != null ? endereco.getCidade().getId() : null;
        this.cidadeNome = endereco.getCidade() != null ? endereco.getCidade().getNome() : null;
        this.estadoId = endereco.getEstado() != null ? endereco.getEstado().getId() : null;
        this.estadoSigla = endereco.getEstado() != null ? endereco.getEstado().getSigla() : null;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getRuaId() { return ruaId; }
    public void setRuaId(String ruaId) { this.ruaId = ruaId; }

    public String getRuaNome() { return ruaNome; }
    public void setRuaNome(String ruaNome) { this.ruaNome = ruaNome; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairroId() { return bairroId; }
    public void setBairroId(String bairroId) { this.bairroId = bairroId; }

    public String getBairroDescricao() { return bairroDescricao; }
    public void setBairroDescricao(String bairroDescricao) { this.bairroDescricao = bairroDescricao; }

    public String getCidadeId() { return cidadeId; }
    public void setCidadeId(String cidadeId) { this.cidadeId = cidadeId; }

    public String getCidadeNome() { return cidadeNome; }
    public void setCidadeNome(String cidadeNome) { this.cidadeNome = cidadeNome; }

    public String getEstadoId() { return estadoId; }
    public void setEstadoId(String estadoId) { this.estadoId = estadoId; }

    public String getEstadoSigla() { return estadoSigla; }
    public void setEstadoSigla(String estadoSigla) { this.estadoSigla = estadoSigla; }
}
