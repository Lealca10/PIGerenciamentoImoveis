package com.gerenciamento.imoveis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "etapas")
public class Etapa {

    @Id
    private String id;

    private Integer ordem;

    // Constructors
    public Etapa() {}

    public Etapa(String id, Integer ordem) {
        this.id = id;
        this.ordem = ordem;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}