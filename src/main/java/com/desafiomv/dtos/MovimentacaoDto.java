package com.desafiomv.dtos;

import com.desafiomv.entidades.Movimentacao;
import com.desafiomv.enums.TipoDeTransacao;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.desafiomv.entidades.Movimentacao}
 */
public record MovimentacaoDto(String descricao,
                              String data,
                              BigDecimal valor,
                              TipoDeTransacao tipoDeTransacao) implements Serializable {

}