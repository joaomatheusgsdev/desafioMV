package com.desafiomv.services;

import com.desafiomv.repositorios.ClienteRepository;
import com.desafiomv.utils.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodosAtivos() {

        return clienteRepository.findAllByHabilitadoTrue();
    }
}
