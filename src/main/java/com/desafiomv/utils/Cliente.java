package com.desafiomv.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "cliente")
public class Cliente {

    public Cliente() {
    }


    public Cliente(Long id, String nome, String email, Enum tipoCliente, Boolean habilitado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoCliente = tipoCliente;
        this.habilitado = habilitado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;

    protected Enum tipoCliente;

    @JsonIgnore
    @Column
    private Boolean habilitado;
}
