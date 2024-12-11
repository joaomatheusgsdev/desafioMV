package com.desafiomv.utils;

import com.desafiomv.dtos.*;

import com.desafiomv.enums.TipoConta;
import com.desafiomv.enums.TipoDeTransacao;
import com.desafiomv.services.RelatorioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class cargaInicial implements CommandLineRunner {

    final RestTemplate restTemplate;

    @Value("${url.api}")
    private String api;

    public cargaInicial(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
        // Carga de contas

        String codigoDaEmpresa = "1";

        String urlEmpresa = api + "/empresas/";
        String urlConta = api + "/contas/";
        String urlMovimentacao = api + "/movimentacoes/";
        String urlClientes = api + "/clientes/23101966000110";



        List<ContaDto> contas = Arrays.asList(
                new ContaDto("001", "0001", "000001", "0", "1", TipoConta.CORRENTE, null, BigDecimal.valueOf(4000))
        );

        // PASSO 1 - Criação da empresa
        EmpresaDTO empresa = new EmpresaDTO("EMPRESATESTE", "empresaTeste@empresa.com", "23101966000110", contas);
        restTemplate.postForObject(urlEmpresa, empresa, EmpresaDTO.class);




        List<MovimentacaoDto> movimentacoes = Arrays.asList(
                new MovimentacaoDto("Pagamento de conta", "2021-10-10", BigDecimal.valueOf(100), TipoDeTransacao.DEBITO, ZonedDateTime.now()),
                new MovimentacaoDto("Recebimento de Pix", "2021-10-10", BigDecimal.valueOf(200), TipoDeTransacao.CREDITO,ZonedDateTime.now())
        );


        List<MovimentacaoDto> movimentacoes1 = Arrays.asList(
                new MovimentacaoDto("Pagamento de conta", "2021-10-10", BigDecimal.valueOf(100), TipoDeTransacao.DEBITO, ZonedDateTime.now()),
                new MovimentacaoDto("Recebimento de Pix", "2021-10-10", BigDecimal.valueOf(200), TipoDeTransacao.CREDITO,ZonedDateTime.now())
        );

        List<MovimentacaoDto> movimentacoes2 = Arrays.asList(
                new MovimentacaoDto("Pagamento de conta 2", "2021-10-10", BigDecimal.valueOf(100), TipoDeTransacao.DEBITO, ZonedDateTime.now()),
                new MovimentacaoDto("Recebimento de Pix 2", "2021-10-10", BigDecimal.valueOf(200), TipoDeTransacao.CREDITO,ZonedDateTime.now())
        );

        List<MovimentacaoDto> movimentacoes3 = Arrays.asList(
                new MovimentacaoDto("Pagamento de conta 3", "2021-10-10", BigDecimal.valueOf(100), TipoDeTransacao.DEBITO, ZonedDateTime.now()),
                new MovimentacaoDto("Recebimento de Pix 3","2021-10-10", BigDecimal.valueOf(200), TipoDeTransacao.CREDITO,ZonedDateTime.now())
        );


        ClienteDTO cliente1 = getCliente("Cliente 1", movimentacoes);
        ClienteDTO cliente2 = getClientePJ("Cliente 2", movimentacoes2);
        ClienteDTO cliente3 = getClientePJ2("Cliente 2", movimentacoes2);




        restTemplate.postForObject(urlClientes, cliente1, ClienteDTO.class);
        restTemplate.postForObject(urlClientes, cliente2, ClienteDTO.class);
        restTemplate.postForObject(urlClientes, cliente3, ClienteDTO.class);


        RelatorioService relatorioService = new RelatorioService();
        relatorioService.gerarRelatorio();

    }



    private static ClienteDTO getCliente(String nome, List<MovimentacaoDto> movimentacaoDtos) {

        Set<MovimentacaoDto> movimentacaoSet = new HashSet<>();

        movimentacaoDtos.forEach(movimentacaoSet::add);

        List<ContaDto> contasCliente = Arrays.asList(
                new ContaDto("001", "0001", "123456", "7", "1", TipoConta.CORRENTE, movimentacaoSet, BigDecimal.valueOf(1000)));

        EnderecoDto endereco = new EnderecoDto("54100230", "Rua teste", "123", "Bairro teste", "Cidade teste", "Estado teste", "123456");


        return new ClienteDTO(nome, "", "testeEmail@gmail.com", "1", true, "70520182440", ZonedDateTime.now(), "", endereco, contasCliente);
    }



    private static ClienteDTO getClientePJ(String nome, List<MovimentacaoDto> movimentacaoDtos) {

        Set<MovimentacaoDto> movimentacaoSet = new HashSet<>();

        movimentacaoDtos.forEach(movimentacaoSet::add);

        List<ContaDto> contasCliente = Arrays.asList(
                new ContaDto("00", "0002", "123456", "7", "1", TipoConta.CORRENTE, movimentacaoSet, BigDecimal.valueOf(1000))
        );

        EnderecoDto endereco = new EnderecoDto("54100230", "Rua teste", "123", "Bairro teste", "Cidade teste", "Estado teste", "123456");


        return new ClienteDTO(nome, "18.556.999/0001-15", "testeEmail@gmail.com", "2", true, "", ZonedDateTime.now(), "PJTeste", endereco, contasCliente);
    }

    private static ClienteDTO getClientePJ2(String nome, List<MovimentacaoDto> movimentacaoDtos) {

        Set<MovimentacaoDto> movimentacaoSet = new HashSet<>();

        movimentacaoDtos.forEach(movimentacaoSet::add);

        List<ContaDto> contasCliente = Arrays.asList(
                new ContaDto("00", "0003", "123456", "7", "1", TipoConta.CORRENTE, movimentacaoSet, BigDecimal.valueOf(1000))
        );

        EnderecoDto endereco = new EnderecoDto("54100230", "Rua teste", "123", "Bairro teste", "Cidade teste", "Estado teste", "123456");


        return new ClienteDTO(nome, "18.556.999/0001-15", "testeEmail@gmail.com", "2", true, "", ZonedDateTime.now(), "PJTeste", endereco, contasCliente);
    }
}