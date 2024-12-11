package com.desafiomv.controllers;


import com.desafiomv.entidades.ClientePF;
import com.desafiomv.entidades.ClientePJ;
import com.desafiomv.entidades.Conta;
import com.desafiomv.dtos.ContaDto;
import com.desafiomv.services.ClientePFService;
import com.desafiomv.services.ClientePJService;
import com.desafiomv.services.ContaService;
import com.desafiomv.utils.Cliente;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {


    final ContaService contaService;
    final ClientePFService clientePFService;
    final ClientePJService clientePJService;


    public ContaController(ContaService contaService, ClientePFService clientePFService, ClientePJService clientePJService) {
        this.contaService = contaService;
        this.clientePFService = clientePFService;
        this.clientePJService = clientePJService;

    }

    @PostMapping("/cliente/{identificador}")
    public ResponseEntity<Conta> salvar(@RequestBody @Valid ContaDto contaDTO, @PathVariable String identificador) {

        var cliente = identificador.length() == 14 ? clientePFService.buscarPorCpf(identificador) : clientePJService.buscarPorCnpj(identificador);

        Conta conta = contaService.salvarConta(cliente, contaDTO);

        return ResponseEntity.ok(conta);
    }

    @GetMapping("/")
    public ResponseEntity<List<Conta>> listarContas() {

        List<Conta> conta = contaService.listarContas();

        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("/{numeroAngecia}/{numeroConta}")
    public ResponseEntity<Void> excluirConta(@PathVariable String numeroAngecia, @PathVariable String numeroConta) {
        contaService.deletarConta(numeroAngecia, numeroConta);
        return ResponseEntity.ok().build();
    }
}
