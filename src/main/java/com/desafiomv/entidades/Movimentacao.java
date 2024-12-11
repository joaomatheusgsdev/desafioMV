package com.desafiomv.entidades;

import com.desafiomv.enums.TipoDeTransacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.desafiomv.entidades.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Table(name = "movimentacao")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String descricao;

    private String data;

    private BigDecimal valor;

    private ZonedDateTime dataDeCriacao;

    private ZonedDateTime dataDeAtualizacao;

    private TipoDeTransacao tipoDeTransacao;

    @ManyToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    @JsonBackReference
    private Conta conta;

    public Movimentacao(Long id, String descricao, String data, BigDecimal valor, ZonedDateTime dataDeCriacao, ZonedDateTime dataDeAtualizacao, TipoDeTransacao tipoDeTransacao, Conta conta) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = BigDecimal.valueOf(0.0);
        this.dataDeCriacao = ZonedDateTime.now();
        this.dataDeAtualizacao = dataDeAtualizacao;
        this.tipoDeTransacao = tipoDeTransacao;
        this.conta = conta;
    }

    public Movimentacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ZonedDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(ZonedDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public ZonedDateTime getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void setDataDeAtualizacao(ZonedDateTime dataDeAtualizacao) {
        this.dataDeAtualizacao = dataDeAtualizacao;
    }

    public TipoDeTransacao getTipoDeTransacao() {
        return tipoDeTransacao;
    }

    public void setTipoDeTransacao(TipoDeTransacao tipoDeTransacao) {
        this.tipoDeTransacao = tipoDeTransacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}