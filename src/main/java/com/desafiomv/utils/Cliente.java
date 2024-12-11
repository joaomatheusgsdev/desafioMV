package com.desafiomv.utils;

import com.desafiomv.entidades.*;
import com.desafiomv.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, TipoCliente tipoCliente, Boolean habilitado, Endereco endereco, Set<Conta> contas, Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.habilitado = habilitado;
        this.endereco = endereco;
        this.contas = contas;
        this.empresa = empresa;
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
    @JoinColumn(name = "endereco_id")
    @JsonManagedReference
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    Set<Conta> contas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    @JsonIgnore
    private Empresa empresa;


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

    public Set<Conta> getContas() {
        return contas;
    }

    public void setContas(Set<Conta> contas) {
        this.contas = contas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipoCliente=" + tipoCliente +
                ", habilitado=" + habilitado +
                ", endereco=" + endereco +
                ", contas=" + contas +
                ", empresa=" + empresa +
                '}';
    }
}
