package com.desafiomv.entidades;

import com.desafiomv.enums.TipoCliente;
import com.desafiomv.utils.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "cliente_pf")
public class ClientePF extends Cliente {

    public ClientePF() {
    }

    public ClientePF(Long id, String nome, String email, TipoCliente tipoCliente, Boolean habilitado, String cpf, ZonedDateTime dataNascimento) {
        super(id, nome, email, tipoCliente, habilitado);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        super.tipoCliente = TipoCliente.PESSOAFISICA;
    }

    @Column
    private String cpf;

    @Column
    private ZonedDateTime dataNascimento;
}