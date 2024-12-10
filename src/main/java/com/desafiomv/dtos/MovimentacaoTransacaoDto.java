package com.desafiomv.dtos;

import com.desafiomv.enums.TipoDeTransacao;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.desafiomv.entidades.Movimentacao}
 */
public record MovimentacaoTransacaoDto(String descricao,
                                       BigDecimal valor,
                                       TipoDeTransacao tipoDeTransacao,
                                       ContaDto contaDto) implements Serializable {
}