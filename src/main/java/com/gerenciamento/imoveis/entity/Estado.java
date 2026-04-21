package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 2, unique = true)
    private String sigla;

    @Column(nullable = false, unique = true)
    private String nome;

    public Estado(String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }
}
