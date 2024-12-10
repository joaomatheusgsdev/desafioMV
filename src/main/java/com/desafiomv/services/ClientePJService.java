package com.desafiomv.services;

import com.desafiomv.dtos.ClienteDTO;
import com.desafiomv.entidades.ClientePJ;
import com.desafiomv.enums.TipoCliente;
import com.desafiomv.repositorios.*;
import org.springframework.stereotype.Service;

@Service
public class ClientePJService {

    final ClientePJRepository clientePJRepository;

    public ClientePJService(ClientePJRepository clientePJRepository) {
        this.clientePJRepository = clientePJRepository;
    }

    public ClientePJ salvar(ClienteDTO clienteDTO) {

        ClientePJ clientePJ = new ClientePJ();

        clientePJ.setNome(clienteDTO.nome());
        clientePJ.setEmail(clienteDTO.email());
        clientePJ.setTipoCliente(TipoCliente.toEnum(2));
        clientePJ.setCnpj(clienteDTO.cnpj());
        clientePJ.setHabilitado(clienteDTO.habilitado());
        clientePJ.setRazaoSocial(clienteDTO.razaoSocial());


        return clientePJRepository.save(clientePJ);
    }
}
