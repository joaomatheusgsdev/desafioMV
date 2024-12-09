package com.desafiomv.entidades;

import com.desafiomv.enums.TipoDeTransacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movimentacao")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String codigoBanco;

    private String codigoAgencia;

    private String numeroConta;

    private String digitoConta;

    private TipoDeTransacao tipoDeTransacao;

}