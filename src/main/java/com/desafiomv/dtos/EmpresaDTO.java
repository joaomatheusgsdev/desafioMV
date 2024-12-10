package com.desafiomv.dtos;

import java.util.List;

public record EmpresaDTO(
        String nome,
        String email,
        String cnpj,
        List<ContaDto> contas
) {
}