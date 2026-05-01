package com.gerenciamento.imoveis.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "imoveis")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    private Integer area;
    private Integer quartos;
    private Integer banheiros;
    private Integer vagasGaragem;
    private BigDecimal valorAvaliacao;
    private LocalDate dataAvaliacao;
    private Integer numeroMatricula;
    private String cartorioRegistro;
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "imovel_fotos", joinColumns = @JoinColumn(name = "imovel_id"))
    @Column(name = "foto")
    private List<String> fotosImovel = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EtapaEnum etapa;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String status;

    // Constructors
    public Imovel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getQuartos() {
        return quartos;
    }

    public void setQuartos(Integer quartos) {
        this.quartos = quartos;
    }

    public Integer getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Integer getVagasGaragem() {
        return vagasGaragem;
    }

    public void setVagasGaragem(Integer vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    public BigDecimal getValorAvaliacao() {
        return valorAvaliacao;
    }

    public void setValorAvaliacao(BigDecimal valorAvaliacao) {
        this.valorAvaliacao = valorAvaliacao;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Integer getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(Integer numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getCartorioRegistro() {
        return cartorioRegistro;
    }

    public void setCartorioRegistro(String cartorioRegistro) {
        this.cartorioRegistro = cartorioRegistro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getFotosImovel() {
        return fotosImovel;
    }

    public void setFotosImovel(List<String> fotosImovel) {
        this.fotosImovel = fotosImovel;
    }

    public EtapaEnum getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaEnum etapa) {
        this.etapa = etapa;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}