package com.desafiomv.controllers;

import com.desafiomv.dtos.EmpresaDTO;
import com.desafiomv.entidades.Conta;
import com.desafiomv.entidades.Empresa;
import com.desafiomv.services.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }


    @PostMapping("/")
    public Empresa criar(@RequestBody EmpresaDTO empresaDTO) {


        return empresaService.criar(empresaDTO);
    }


}
