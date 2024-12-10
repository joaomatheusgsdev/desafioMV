package com.desafiomv.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.desafiomv.entidades.Endereco}
 */
public record EnderecoDto(String cep,
                          String logradouro,
                          String numero,
                          String complemento,
                          String bairro,
                          String cidade,
                          String estado) implements Serializable {
}