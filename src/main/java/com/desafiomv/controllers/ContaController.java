package com.desafiomv.controllers;


import com.desafiomv.entidades.Conta;
import com.desafiomv.dtos.ContaDto;
import com.desafiomv.services.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
public class ContaController {


    final ContaService contaService;


    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/")
    public ResponseEntity<Conta> salvar(@RequestBody @Valid ContaDto contaDTO) {

        Conta conta = contaService.salvarConta(contaDTO);

        return ResponseEntity.ok(conta);
    }
}
