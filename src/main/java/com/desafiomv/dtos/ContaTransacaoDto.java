package com.desafiomv.dtos;

import com.desafiomv.entidades.Conta;

import java.io.Serializable;

/**
 * DTO for {@link Conta}
 */
public record ContaTransacaoDto(String codigoBanco,
                                String codigoAgencia,
                                String numeroConta,
                                String digitoConta,
                                String digitoAgencia) implements Serializable {
}