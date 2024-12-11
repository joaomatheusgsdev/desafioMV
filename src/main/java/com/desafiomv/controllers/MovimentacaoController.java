package com.desafiomv.controllers;

import com.desafiomv.dtos.MovimentacaoTransacaoDto;
import com.desafiomv.entidades.Movimentacao;
import com.desafiomv.services.MovimentacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }


    @PostMapping("/")
    public ResponseEntity<Movimentacao> salvarMovimentacao(@RequestBody  MovimentacaoTransacaoDto movimentacaoTransacaoDto) {

        Movimentacao mov = movimentacaoService.salvarMovimentacao(movimentacaoTransacaoDto);

        return ResponseEntity.ok(mov);
    }


    @GetMapping("/")
    public ResponseEntity<List<Movimentacao>> listarMovimentacoes() {
        return ResponseEntity.ok(movimentacaoService.listarMovimentacoes());
    }
}
