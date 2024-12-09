package com.desafiomv.controllers;

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

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        return ResponseEntity.ok(new Cliente());
    }


    @PostMapping("/")
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(new Cliente());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(Long id) {
        return ResponseEntity.ok().build();
    }
}
