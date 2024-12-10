package com.desafiomv.utils;

import com.desafiomv.entidades.*;
import com.desafiomv.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, TipoCliente tipoCliente, Boolean habilitado, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.habilitado = habilitado;
        this.endereco = endereco;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    protected TipoCliente tipoCliente;

    @JsonIgnore
    @Column
    private Boolean habilitado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @JsonIgnore
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
