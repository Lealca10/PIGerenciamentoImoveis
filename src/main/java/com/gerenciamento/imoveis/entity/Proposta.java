package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    private String cliente;

    private BigDecimal valor;

    private String status;

    private LocalDate data;

    // Constructors
    public Proposta() {}

    public Proposta(String id, Imovel imovel, String cliente, BigDecimal valor, String status, LocalDate data) {
        this.id = id;
        this.imovel = imovel;
        this.cliente = cliente;
        this.valor = valor;
        this.status = status;
        this.data = data;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}