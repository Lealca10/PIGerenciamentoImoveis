package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_etapas")
public class HistoricoEtapa {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    private String etapaDe;

    private String etapaPara;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime data;

    // Constructors
    public HistoricoEtapa() {}

    public HistoricoEtapa(String id, Imovel imovel, String etapaDe, String etapaPara, Usuario usuario, LocalDateTime data) {
        this.id = id;
        this.imovel = imovel;
        this.etapaDe = etapaDe;
        this.etapaPara = etapaPara;
        this.usuario = usuario;
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

    public String getEtapaDe() {
        return etapaDe;
    }

    public void setEtapaDe(String etapaDe) {
        this.etapaDe = etapaDe;
    }

    public String getEtapaPara() {
        return etapaPara;
    }

    public void setEtapaPara(String etapaPara) {
        this.etapaPara = etapaPara;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}