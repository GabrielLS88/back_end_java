package com.fluxy.service.service;

import com.fluxy.service.dto.UsuarioResponse;
import com.fluxy.service.modal.Usuario;
import com.fluxy.service.repository.UsuarioDadosRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioDadosRepository repo;

    public UsuarioService(UsuarioDadosRepository repo) {
        this.repo = repo;
    }

    public UsuarioResponse buscarPorEmail(String email) {
        return repo.findByEmailUser(email)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuário com e-mail '" + email + "' não encontrado em nossa base"));
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getNomeUser(),
                usuario.getEmailUser(),
                usuario.getTelefoneUser(),
                usuario.getCpfUser(),
                usuario.getCnpjUser(),
                usuario.getNomeEmpresaUser()
        );
    }
}
