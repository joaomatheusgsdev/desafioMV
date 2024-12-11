package com.desafiomv.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;


public record ClienteDTO(
        @NotNull(message = "O nome não pode ser nulo")
        @NotEmpty(message = "O nome não pode estar vazio")
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        String cnpj,

        @NotNull(message = "O email não pode ser nulo")
        @Email
        @NotEmpty(message = "O email não pode ser vazio")
        @NotBlank(message = "O email não pode estar em branco")

        String email,

        String tipoCliente,

        Boolean habilitado,

        String cpf,
        ZonedDateTime dataNascimento,

        String razaoSocial,

        EnderecoDto endereco,

        List<ContaDto> contas
) {
}