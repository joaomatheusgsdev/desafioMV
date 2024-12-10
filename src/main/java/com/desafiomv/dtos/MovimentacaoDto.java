package com.desafiomv.dtos;

import com.desafiomv.enums.TipoDeTransacao;

import java.io.Serializable;

/**
 * DTO for {@link com.desafiomv.entidades.Movimentacao}
 */
public record MovimentacaoDto(String codigoBanco,
                              String codigoAgencia,
                              String numeroConta,
                              String digitoConta,
                              TipoDeTransacao tipoDeTransacao) implements Serializable {
}