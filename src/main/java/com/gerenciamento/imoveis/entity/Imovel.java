package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "imoveis")
public class Imovel {

    @Id
    private String id;

    private String codigo;

    private String endereco;

    private String cidade;

    private String estado;

    private BigDecimal valor;

    private BigDecimal area;

    @ManyToOne
    @JoinColumn(name = "etapa_atual")
    private Etapa etapaAtual;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    private String status;

    // Constructors
    public Imovel() {}

    public Imovel(String id, String codigo, String endereco, String cidade, String estado,
                  BigDecimal valor, BigDecimal area, Etapa etapaAtual, Usuario responsavel, String status) {
        this.id = id;
        this.codigo = codigo;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.valor = valor;
        this.area = area;
        this.etapaAtual = etapaAtual;
        this.responsavel = responsavel;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Etapa getEtapaAtual() {
        return etapaAtual;
    }

    public void setEtapaAtual(Etapa etapaAtual) {
        this.etapaAtual = etapaAtual;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}