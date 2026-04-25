package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enderecos")
@Data
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 9)
    private String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rua_id", nullable = false)
    private Rua rua;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bairro_id", nullable = false)
    private Bairro bairro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(length = 100)
    private String complemento;

    public Endereco(String cep, Rua rua, Bairro bairro, Cidade cidade, Estado estado, String numero, String complemento) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(String cep, Rua rua, Bairro bairro, Cidade cidade, Estado estado, String numero) {
        this(cep, rua, bairro, cidade, estado, numero, null);
    }

    /**
     * Retorna o endereço completo formatado
     * @return String com endereço completo
     */
    public String getEnderecoCompleto() {
        StringBuilder sb = new StringBuilder();

        if (rua != null && rua.getNome() != null) {
            sb.append(rua.getNome());
        }
        if (numero != null && !numero.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(numero);
        }
        if (complemento != null && !complemento.isEmpty()) {
            if (sb.length() > 0) sb.append(" - ");
            sb.append(complemento);
        }
        if (cep != null && !cep.isEmpty()) {
            if (sb.length() > 0) sb.append(" - ");
            sb.append(cep);
        }
        if (bairro != null && bairro.getDescricao() != null) {
            if (sb.length() > 0) sb.append(" - ");
            sb.append(bairro.getDescricao());
        }
        if (cidade != null && cidade.getNome() != null) {
            if (sb.length() > 0) sb.append(" - ");
            sb.append(cidade.getNome());
        }
        if (estado != null && estado.getSigla() != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(estado.getSigla());
        }

        return sb.toString();
    }
}
