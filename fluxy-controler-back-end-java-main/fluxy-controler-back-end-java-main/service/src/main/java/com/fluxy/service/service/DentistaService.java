package com.fluxy.service.service;

import com.fluxy.service.dto.DentistaRequest;
import com.fluxy.service.dto.DentistaResponse;
import com.fluxy.service.modal.Dentista;
import com.fluxy.service.modal.Usuario;
import com.fluxy.service.repository.DentistaRepository;
import com.fluxy.service.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaService {

    private final DentistaRepository repo;
    private final UsuarioRepository usuarioRepo;

    public DentistaService(DentistaRepository repo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }

    public DentistaResponse criar(DentistaRequest req) {
        Dentista d = new Dentista();

        Usuario u = usuarioRepo.findById(req.getIdUserResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        d.setUsuarioResponsavel(u);
        d.setNome(req.getNomeDentista());
        d.setEmail(req.getEmailDentista());
        d.setTelefone(req.getTelefoneDentista());
        d.setCpf(req.getCpfDentista());
        d.setCnpj(req.getCnpjDentista());
        d.setNomeClinica(req.getNomeClinicaDentista());
        d.setEndereco(req.getEnderecoDentista());
        d.setStatus(req.getStatusDentista() != null ? req.getStatusDentista() : "ATIVO");

        return toResponse(repo.save(d));
    }

    public List<DentistaResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public DentistaResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
    }

    public DentistaResponse atualizar(Long id, DentistaRequest req) {
        Dentista d = repo.findById(id).orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        Usuario u = usuarioRepo.findById(req.getIdUserResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        d.setUsuarioResponsavel(u);
        d.setNome(req.getNomeDentista());
        d.setEmail(req.getEmailDentista());
        d.setTelefone(req.getTelefoneDentista());
        d.setCpf(req.getCpfDentista());
        d.setCnpj(req.getCnpjDentista());
        d.setNomeClinica(req.getNomeClinicaDentista());
        d.setEndereco(req.getEnderecoDentista());
        d.setStatus(req.getStatusDentista());

        return toResponse(repo.save(d));
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }

    private DentistaResponse toResponse(Dentista d) {
        DentistaResponse r = new DentistaResponse();
        r.setId(d.getId());
        r.setIdUserResponsavel(d.getUsuarioResponsavel().getIdUser());
        r.setNomeDentista(d.getNome());
        r.setEmailDentista(d.getEmail());
        r.setTelefoneDentista(d.getTelefone());
        r.setCpfDentista(d.getCpf());
        r.setCnpjDentista(d.getCnpj());
        r.setNomeClinicaDentista(d.getNomeClinica());
        r.setEnderecoDentista(d.getEndereco());
        r.setStatusDentista(d.getStatus());
        return r;
    }
}
