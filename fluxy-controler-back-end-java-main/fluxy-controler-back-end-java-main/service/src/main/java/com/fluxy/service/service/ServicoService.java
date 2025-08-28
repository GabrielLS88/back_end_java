package com.fluxy.service.service;

import com.fluxy.service.dto.ServicoRequest;
import com.fluxy.service.dto.ServicoResponse;
import com.fluxy.service.modal.Servico;
import com.fluxy.service.modal.Usuario;
import com.fluxy.service.repository.ServicoRepository;
import com.fluxy.service.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository repo;
    private final UsuarioRepository usuarioRepo;

    public ServicoService(ServicoRepository repo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }

    public ServicoResponse criar(ServicoRequest req) {
        Servico s = new Servico();
        Usuario u = usuarioRepo.findById(req.getIdUserResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        s.setUsuarioResponsavel(u);
        s.setNome(req.getNomeServico());
        s.setValor(req.getValorServico());
        s.setStatus(req.getStatusServico() != null ? req.getStatusServico() : "ATIVO");

        return toResponse(repo.save(s));
    }

    public List<ServicoResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ServicoResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public ServicoResponse atualizar(Long id, ServicoRequest req) {
        Servico s = repo.findById(id).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        Usuario u = usuarioRepo.findById(req.getIdUserResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        s.setUsuarioResponsavel(u);
        s.setNome(req.getNomeServico());
        s.setValor(req.getValorServico());
        s.setStatus(req.getStatusServico());

        return toResponse(repo.save(s));
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }

    private ServicoResponse toResponse(Servico s) {
        ServicoResponse r = new ServicoResponse();
        r.setId(s.getId());
        r.setIdUserResponsavel(s.getUsuarioResponsavel().getIdUser());
        r.setNomeServico(s.getNome());
        r.setValorServico(s.getValor());
        r.setStatusServico(s.getStatus());
        return r;
    }
}
