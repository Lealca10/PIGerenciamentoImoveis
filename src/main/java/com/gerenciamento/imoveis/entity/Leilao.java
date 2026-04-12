package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "leiloes")
public class Leilao {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    private Integer numero;

    private LocalDate data;

    private BigDecimal valorMinimo;

    private String status;

    // Constructors
    public Leilao() {}

    public Leilao(String id, Imovel imovel, Integer numero, LocalDate data, BigDecimal valorMinimo, String status) {
        this.id = id;
        this.imovel = imovel;
        this.numero = numero;
        this.data = data;
        this.valorMinimo = valorMinimo;
        this.status = status;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(BigDecimal valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}