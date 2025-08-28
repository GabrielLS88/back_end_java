package com.fluxy.service.controller;

import com.fluxy.service.dto.ReciboRequest;
import com.fluxy.service.dto.ReciboResponse;
import com.fluxy.service.service.ReciboService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fluxy/recibos")
public class ReciboController {

    private final ReciboService service;

    public ReciboController(ReciboService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReciboResponse> criar(@RequestBody ReciboRequest req) {
        return ResponseEntity.ok(service.criar(req));
    }

    @GetMapping
    public ResponseEntity<List<ReciboResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReciboResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReciboResponse> atualizar(@PathVariable Long id, @RequestBody ReciboRequest req) {
        return ResponseEntity.ok(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

