package com.desafiomv.controllers;

import com.desafiomv.dtos.ClienteDTO;
import com.desafiomv.repositorios.ClienteRepository;
import com.desafiomv.services.ClientePFService;
import com.desafiomv.services.ClientePJService;
import com.desafiomv.services.ClienteService;
import com.desafiomv.utils.Cliente;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteContoller {

    final ClientePFService clientePFService;
    final ClienteService clienteService;
    final ClientePJService clientePJService;

    public ClienteContoller(ClientePFService clientePFService, ClienteService clienteService, ClientePJService clientePJService) {
        this.clientePFService = clientePFService;
        this.clienteService = clienteService;
        this.clientePJService = clientePJService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodosAtivos();

        return ResponseEntity.ok(clientes);
    }


    @PostMapping("/{cnpjEmpresa}")
    public ResponseEntity<Cliente> salvar(@PathVariable String cnpjEmpresa, @RequestBody ClienteDTO clienteDTO) {

        var cliente = clienteService.criarCliente(clienteDTO, cnpjEmpresa);

        return ResponseEntity.ok(cliente);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(Long id) {
        return ResponseEntity.ok().build();
    }
}
