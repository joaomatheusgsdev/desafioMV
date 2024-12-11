package com.desafiomv.services;

import com.desafiomv.entidades.Conta;
import com.desafiomv.dtos.ContaDto;
import com.desafiomv.entidades.Movimentacao;
import com.desafiomv.repositorios.ContaRepository;
import com.desafiomv.utils.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ContaService {

    final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }


    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta salvarConta(Cliente cliente, ContaDto contaDto) {


        if(contaRepository.findByNumeroContaAndCodigoAgencia(contaDto.numeroConta(), contaDto.codigoAgencia()) != null) {
            throw new RuntimeException("Conta já cadastrada");
        }



        Conta conta = new Conta();

        conta.setDigitoConta(contaDto.digitoConta());
        conta.setCodigoBanco(contaDto.codigoBanco());

        conta.setNumeroConta(contaDto.numeroConta());
        conta.setDigitoConta(contaDto.digitoConta());

        conta.setCodigoAgencia(contaDto.codigoAgencia());
        conta.setDigitoAgencia(contaDto.digitoAgencia());
        
        conta.setTipoConta(contaDto.tipoConta());


        if(contaDto.movimentacoes() != null && !contaDto.movimentacoes().isEmpty()) {

            contaDto.movimentacoes().forEach(movimentacaoDto -> {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setDescricao(movimentacaoDto.descricao());
                movimentacao.setTipoDeTransacao(movimentacaoDto.tipoDeTransacao());
                conta.addMovimentacao(movimentacao);
            });

        }

        conta.atualizacaoDeSaldo();
        cliente.addConta(conta);

        return contaRepository.save(conta);
    }

    public Conta buscarPorNumeroContaAndCodigoAgencia(String numeroConta, String codigoAgencia) {
        return contaRepository.findByNumeroContaAndCodigoAgencia(numeroConta, codigoAgencia);
    }

    @Transactional
    public void deletarConta(String agencia, String numeroConta) {

        Conta conta = contaRepository.findContaByCodigoAgenciaAndNumeroConta(agencia, numeroConta);

        if (!conta.getMovimentacoes().isEmpty()) {
           conta.setHabilitada(false);
           conta.setDataAtualização(ZonedDateTime.now());
           contaRepository.save(conta);
        } else {
           contaRepository.delete(conta);
        }

    }
}
