package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ruas", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "cep", "cidade_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rua {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 9)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    public Rua(String nome, String cep, Cidade cidade) {
        this.nome = nome;
        this.cep = cep;
        this.cidade = cidade;
    }
}
