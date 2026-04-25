package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bairros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String descricao;

    public Bairro(String descricao) {
        this.descricao = descricao;
    }
}
