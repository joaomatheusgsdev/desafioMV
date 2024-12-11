package com.desafiomv.services;

import com.desafiomv.dtos.ClienteDTO;
import com.desafiomv.entidades.*;
import com.desafiomv.enums.TipoCliente;
import com.desafiomv.repositorios.*;
import org.springframework.stereotype.Service;

@Service
public class ClientePJService {

    final ClientePJRepository clientePJRepository;
    final EmpresaService empresaService;

    public ClientePJService(ClientePJRepository clientePJRepository, EmpresaService empresaService) {
        this.clientePJRepository = clientePJRepository;
        this.empresaService = empresaService;
    }

    public ClientePJ salvar(ClienteDTO clienteDTO, String cnpj) {

        Empresa empresa = empresaService.buscarPorCnpj(cnpj);

        ClientePJ clientePJ = new ClientePJ();

        clientePJ.setNome(clienteDTO.nome());
        clientePJ.setEmail(clienteDTO.email());
        clientePJ.setTipoCliente(TipoCliente.PESSOAJURIDICA);
        clientePJ.setCnpj(clienteDTO.cnpj());
        clientePJ.setHabilitado(clienteDTO.habilitado());
        clientePJ.setRazaoSocial(clienteDTO.razaoSocial());
        clientePJ.setEmpresa(empresa);

        Endereco endereco = new Endereco();
        endereco.setCep(clienteDTO.endereco().cep());
        endereco.setCidade(clienteDTO.endereco().cidade());
        endereco.setEstado(clienteDTO.endereco().estado());
        endereco.setLogradouro(clienteDTO.endereco().logradouro());
        endereco.setNumero(clienteDTO.endereco().numero());
        endereco.setComplemento(clienteDTO.endereco().complemento());
        endereco.setBairro(clienteDTO.endereco().bairro());
        clientePJ.setEndereco(endereco);

        for (int i = 0; i < clienteDTO.contas().size(); i++) {

            Conta conta = new Conta();
            conta.setCodigoAgencia(clienteDTO.contas().get(i).codigoAgencia());
            conta.setCodigoBanco(clienteDTO.contas().get(i).codigoBanco());
            conta.setNumeroConta(clienteDTO.contas().get(i).numeroConta());
            conta.setDigitoConta(clienteDTO.contas().get(i).digitoConta());
            conta.setDigitoAgencia(clienteDTO.contas().get(i).digitoAgencia());
            conta.setTipoConta(clienteDTO.contas().get(i).tipoConta());
            conta.setCliente(clientePJ);
            clientePJ.addConta(conta);

            clienteDTO.contas().get(i).movimentacoes().forEach(movimentacaoDto -> {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setDescricao(movimentacaoDto.descricao());
                movimentacao.setTipoDeTransacao(movimentacaoDto.tipoDeTransacao());
                movimentacao.setValor(movimentacaoDto.valor());
                movimentacao.setDataDeCriacao(movimentacaoDto.dataDeCriacao());
                movimentacao.setConta(conta);
                conta.atualizacaoDeSaldo();
                conta.addMovimentacao(movimentacao);
            });

        }



        empresa.addClientes(clientePJ);
        return clientePJRepository.save(clientePJ);
    }


    public ClientePJ buscarPorCnpj(String cnpj) {
        return clientePJRepository.findClientePJByCnpj(cnpj);
    }
}
