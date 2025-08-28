package com.fluxy.service.controller;

import com.fluxy.service.dto.ComandaRequest;
import com.fluxy.service.dto.ComandaResponse;
import com.fluxy.service.service.ComadaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fluxy/comanda")
public class ComandaController {

    private final ComadaService service;

    public ComandaController(ComadaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ComandaResponse> criar(@RequestBody ComandaRequest req) {
        return ResponseEntity.ok(service.criar(req));
    }

    @GetMapping
    public ResponseEntity<List<ComandaResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComandaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComandaResponse> atualizar(@PathVariable Long id, @RequestBody ComandaRequest req) {
        return ResponseEntity.ok(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
