package com.desafiomv.dtos;

import com.desafiomv.entidades.Conta;
import com.desafiomv.enums.TipoConta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link Conta}
 */
public record ContaDto(String codigoBanco,
                       String codigoAgencia,
                       String numeroConta,
                       String digitoConta,
                       String digitoAgencia,
                       TipoConta tipoConta,
                       Set<MovimentacaoDto> movimentacoes,
                       BigDecimal saldo) implements Serializable {

}