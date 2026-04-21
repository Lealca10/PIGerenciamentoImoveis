package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enderecos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rua_id", nullable = false)
    private Rua rua;

    @Column(nullable = false, length = 10)
    private String numero;

    @Column(length = 100)
    private String complemento;

    public Endereco(Rua rua, String numero, String complemento) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(Rua rua, String numero) {
        this.rua = rua;
        this.numero = numero;
    }

    /**
     * Retorna o endereço completo formatado
     * @return String com endereço completo
     */
    public String getEnderecoCompleto() {
        StringBuilder sb = new StringBuilder();
        
        if (rua != null) {
            if (rua.getNome() != null) {
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
            if (rua.getCep() != null && !rua.getCep().isEmpty()) {
                if (sb.length() > 0) sb.append(" - ");
                sb.append(rua.getCep());
            }
            if (rua.getCidade() != null) {
                if (rua.getCidade().getNome() != null) {
                    if (sb.length() > 0) sb.append(" - ");
                    sb.append(rua.getCidade().getNome());
                }
                if (rua.getCidade().getEstado() != null && rua.getCidade().getEstado().getSigla() != null) {
                    if (sb.length() > 0) sb.append(", ");
                    sb.append(rua.getCidade().getEstado().getSigla());
                }
            }
        }
        
        return sb.toString();
    }
}
