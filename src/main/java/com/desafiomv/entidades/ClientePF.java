package com.desafiomv.entidades;

import com.desafiomv.enums.*;
import com.desafiomv.utils.Cliente;
import jakarta.persistence.*;
import lombok.*;


import java.time.ZonedDateTime;
import java.util.Set;

@Builder
@Entity
@Table(name = "cliente_pf")
@ToString
@PrimaryKeyJoinColumn(name = "cliente_id")
public class ClientePF extends Cliente {

    public ClientePF() {
        super();
    }

    public ClientePF(Long id, String nome, String email, TipoCliente tipoCliente, Boolean habilitado, Endereco endereco, Set<Conta> contas, Empresa empresa, String cpf, ZonedDateTime dataNascimento) {
        super(id, nome, email, tipoCliente, habilitado, endereco, contas, empresa);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Column
    private String cpf;

    @Column
    private ZonedDateTime dataNascimento;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ZonedDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(ZonedDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    @Override
    public String toString() {
        return "ClientePF{" +
                "cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", tipoCliente=" + tipoCliente +
                "} " + super.toString();
    }
}