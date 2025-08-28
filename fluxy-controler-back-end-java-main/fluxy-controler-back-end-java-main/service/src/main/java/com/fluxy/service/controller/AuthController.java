package com.fluxy.service.controller;

import com.fluxy.service.service.AuthService;
import com.fluxy.service.dto.AuthRequest;
import com.fluxy.service.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fluxy/oauth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> autenticar(@RequestBody AuthRequest request) {
        String token = authService.autenticar(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
