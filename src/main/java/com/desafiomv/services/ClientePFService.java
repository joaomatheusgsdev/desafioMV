package com.desafiomv.services;


import com.desafiomv.dtos.ClienteDTO;
import com.desafiomv.entidades.*;
import com.desafiomv.enums.TipoCliente;
import com.desafiomv.repositorios.ClienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientePFService {

    final ClienteRepository clienteRepository;

    public ClientePFService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClientePF salvar(ClienteDTO clienteDTO) {

        ClientePF clientePF = new ClientePF();

        clientePF.setNome(clienteDTO.nome());
        clientePF.setEmail(clienteDTO.email());
        clientePF.setTipoCliente(TipoCliente.toEnum(1));
        clientePF.setCpf(clienteDTO.cpf());
        clientePF.setDataNascimento(clienteDTO.dataNascimento());
        clientePF.setHabilitado(clienteDTO.habilitado());

        Endereco endereco = new Endereco();
        endereco.setCep(clienteDTO.endereco().cep());
        endereco.setCidade(clienteDTO.endereco().cidade());
        endereco.setEstado(clienteDTO.endereco().estado());
        endereco.setLogradouro(clienteDTO.endereco().logradouro());
        endereco.setNumero(clienteDTO.endereco().numero());
        endereco.setComplemento(clienteDTO.endereco().complemento());
        endereco.setBairro(clienteDTO.endereco().bairro());
        clientePF.setEndereco(endereco);


        System.out.println(clientePF.toString());


        return clienteRepository.save(clientePF);
    }




}
