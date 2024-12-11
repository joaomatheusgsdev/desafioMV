package com.desafiomv.services;


import com.desafiomv.dtos.ClienteDTO;
import com.desafiomv.entidades.*;
import com.desafiomv.enums.TipoCliente;
import com.desafiomv.repositorios.ClientePFRepository;
import com.desafiomv.repositorios.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ClientePFService {

    final ClienteRepository clienteRepository;
    final EmpresaService empresaService;
    private final ClientePFRepository clientePFRepository;

    public ClientePFService(ClienteRepository clienteRepository, EmpresaService empresaService, ClientePFRepository clientePFRepository) {
        this.clienteRepository = clienteRepository;
        this.empresaService = empresaService;
        this.clientePFRepository = clientePFRepository;
    }

    @Transactional
    public ClientePF salvar(ClienteDTO clienteDTO, String cnpj) {

        Empresa empresa = empresaService.buscarPorCnpj(cnpj);

        ClientePF clientePF = new ClientePF();

        clientePF.setNome(clienteDTO.nome());
        clientePF.setEmail(clienteDTO.email());
        clientePF.setTipoCliente(TipoCliente.PESSOAFISICA);
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
        clientePF.setEmpresa(empresa);

        for (int i = 0; i < clienteDTO.contas().size(); i++) {

            Conta conta = new Conta();
            conta.setCodigoAgencia(clienteDTO.contas().get(i).codigoAgencia());
            conta.setCodigoBanco(clienteDTO.contas().get(i).codigoBanco());
            conta.setNumeroConta(clienteDTO.contas().get(i).numeroConta());
            conta.setDigitoConta(clienteDTO.contas().get(i).digitoConta());
            conta.setDigitoAgencia(clienteDTO.contas().get(i).digitoAgencia());
            conta.setTipoConta(clienteDTO.contas().get(i).tipoConta());

            conta.setCliente(clientePF);
            clientePF.addConta(conta);

            clienteDTO.contas().get(i).movimentacoes().forEach(movimentacaoDto -> {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setDescricao(movimentacaoDto.descricao());
                movimentacao.setTipoDeTransacao(movimentacaoDto.tipoDeTransacao());
                movimentacao.setValor(movimentacaoDto.valor());
                movimentacao.setDataDeCriacao(movimentacaoDto.dataDeCriacao());
                movimentacao.setConta(conta);

                conta.addMovimentacao(movimentacao);
            });

        }

        empresa.addClientes(clientePF);
        return clienteRepository.save(clientePF);
 }

    public ClientePF buscarPorCpf(String cpf) {
        return clientePFRepository.findClientePFByCpf(cpf);
    }
}
