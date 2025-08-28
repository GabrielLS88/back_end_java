package com.fluxy.service.service;

import com.fluxy.service.modal.Usuario;
import com.fluxy.service.repository.UsuarioRepository;
import com.fluxy.service.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public String autenticar(String email, String senha) {
        // Busca o usuário pelo e-mail
        Usuario usuario = repository.findByEmailUser(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Valida a senha (comparação texto puro)
        if (!usuario.getSenhaUser().equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }

        // Gera e retorna o token JWT com o e-mail do usuário
        return jwtUtil.generateToken(usuario.getEmailUser());
    }
}
