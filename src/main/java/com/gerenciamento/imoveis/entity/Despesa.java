package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesas")
public class Despesa {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    private String categoria;

    private BigDecimal valor;

    private LocalDate data;

    private Boolean aprovado;

    // Constructors
    public Despesa() {}

    public Despesa(String id, Imovel imovel, String categoria, BigDecimal valor, LocalDate data, Boolean aprovado) {
        this.id = id;
        this.imovel = imovel;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.aprovado = aprovado;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }
}