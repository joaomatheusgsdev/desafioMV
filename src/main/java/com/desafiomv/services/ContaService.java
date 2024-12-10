package com.desafiomv.services;

import com.desafiomv.entidades.Conta;
import com.desafiomv.dtos.ContaDto;
import com.desafiomv.entidades.Movimentacao;
import com.desafiomv.repositorios.ContaRepository;
import org.springframework.stereotype.Service;

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

    public Conta salvarConta(ContaDto contaDto) {

        Conta conta = new Conta();

        conta.setDigitoConta(contaDto.digitoConta());
        conta.setCodigoBanco(contaDto.codigoBanco());

        conta.setNumeroConta(contaDto.numeroConta());
        conta.setDigitoConta(contaDto.digitoConta());

        conta.setCodigoAgencia(contaDto.codigoAgencia());
        conta.setDigitoAgencia(contaDto.digitoAgencia());


        if(contaDto.movimentacoes() != null && !contaDto.movimentacoes().isEmpty()) {

            contaDto.movimentacoes().forEach(movimentacaoDto -> {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setDescricao(movimentacaoDto.descricao());
                movimentacao.setTipoDeTransacao(movimentacaoDto.tipoDeTransacao());
                conta.addMovimentacao(movimentacao);
            });

        }


        return contaRepository.save(conta);
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id).orElse(null);
    }

    public Conta buscarPorNumeroContaAndCodigoAgencia(String numeroConta, String codigoAgencia) {
        return contaRepository.findByNumeroContaAndCodigoAgencia(numeroConta, codigoAgencia);
    }
}
