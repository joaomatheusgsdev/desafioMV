package com.desafiomv.services;

import com.desafiomv.dtos.ClienteDTO;
import com.desafiomv.entidades.ClientePF;
import com.desafiomv.entidades.ClientePJ;
import com.desafiomv.entidades.Conta;
import com.desafiomv.entidades.Endereco;
import com.desafiomv.repositorios.ClienteRepository;
import com.desafiomv.utils.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    final ClienteRepository clienteRepository;
    final ClientePJService clientePJService;
    final ClientePFService clientePFService;

    public ClienteService(ClienteRepository clienteRepository, ClientePJService clientePJService, ClientePFService clientePFService) {
        this.clienteRepository = clienteRepository;
        this.clientePJService = clientePJService;
        this.clientePFService = clientePFService;
    }

    public Cliente criarCliente(ClienteDTO clienteDto, String cnpj) {

        Cliente c;

        if (clienteDto.cpf().isEmpty() && clienteDto.cnpj().isEmpty()) {
            throw new RuntimeException("O CPF ou CNPJ do cliente é obrigatório");
        }

        if (clienteDto.cpf().isEmpty()) {
            c = clientePJService.salvar(clienteDto, cnpj);
        } else {
            c = clientePFService.salvar(clienteDto, cnpj);
        }


        return c;
    }



    public List<Cliente> listarTodosAtivos() {

        return clienteRepository.findAllByHabilitadoTrue();
    }

    public Cliente buscarPorConta(Conta conta) {
        return clienteRepository.findByContas(conta);
    }
}
