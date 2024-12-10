package com.desafiomv.services;

import com.desafiomv.dtos.MovimentacaoTransacaoDto;
import com.desafiomv.entidades.Conta;
import com.desafiomv.entidades.Movimentacao;
import com.desafiomv.repositorios.ClienteRepository;
import com.desafiomv.repositorios.ContaRepository;
import com.desafiomv.repositorios.MovimentacaoRepository;
import com.desafiomv.utils.Cliente;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    final MovimentacaoRepository movimentacaoRepository;
    final ContaService contaService;
    final ClienteService clienteService;
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ContaService contaService, ClienteService clienteService, ClienteRepository clienteRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.contaService = contaService;
        this.clienteService = clienteService;
    }


    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoRepository.findAll();
    }

    public Movimentacao salvarMovimentacao(MovimentacaoTransacaoDto movimentacaoDto) {

        Conta conta = contaService.buscarPorNumeroContaAndCodigoAgencia(movimentacaoDto.contaDto().numeroConta(), movimentacaoDto.contaDto().codigoAgencia());

        if(conta == null) {
           throw new RuntimeException("A conta que está tentando movimentar não existe");
        }

        Cliente c = clienteService.buscarPorConta(conta);

        if (c == null) {
            throw new RuntimeException("O cliente que está tentando movimentar não existe");
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setConta(conta);
        movimentacao.setValor(movimentacaoDto.valor());
        movimentacao.setDescricao(movimentacaoDto.descricao());
        movimentacao.setTipoDeTransacao(movimentacaoDto.tipoDeTransacao());
        movimentacao.setDataDeCriacao(ZonedDateTime.now());

        return movimentacaoRepository.save(movimentacao);
    }

    public Movimentacao buscarPorId(Long id) {
        return movimentacaoRepository.findById(id).orElse(null);
    }

    public Movimentacao alterar(Long id) {

        Movimentacao movimentacao = movimentacaoRepository.findById(id).orElse(null);





        return movimentacaoRepository.save(movimentacao);
    }
}
