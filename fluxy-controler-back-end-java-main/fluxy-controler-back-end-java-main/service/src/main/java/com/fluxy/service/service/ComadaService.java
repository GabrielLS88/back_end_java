package com.fluxy.service.service;

import com.fluxy.service.dto.ComandaRequest;
import com.fluxy.service.dto.ComandaResponse;
import com.fluxy.service.dto.ServicoResumo;
import com.fluxy.service.modal.Comanda;
import com.fluxy.service.modal.Usuario;
import com.fluxy.service.modal.Dentista;
import com.fluxy.service.modal.Servico;
import com.fluxy.service.repository.ComandaRepository;
import com.fluxy.service.repository.UsuarioRepository;
import com.fluxy.service.repository.DentistaRepository;
import com.fluxy.service.repository.ServicoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComadaService {

    private final ComandaRepository comandaRepo;
    private final UsuarioRepository usuarioRepo;
    private final DentistaRepository dentistaRepo;
    private final ServicoRepository servicoRepo;

    public ComadaService(ComandaRepository comandaRepo,
                          UsuarioRepository usuarioRepo,
                          DentistaRepository dentistaRepo,
                          ServicoRepository servicoRepo) {
        this.comandaRepo = comandaRepo;
        this.usuarioRepo = usuarioRepo;
        this.dentistaRepo = dentistaRepo;
        this.servicoRepo = servicoRepo;
    }

    public ComandaResponse criar(ComandaRequest req) {
        Comanda comanda = new Comanda();
        comanda.setDescricao(req.getDescricaoComanda());
        comanda.setValor(req.getValorComanda());
        comanda.setStatus("ABERTA");

        comanda.setUsuario(usuarioRepo.findById(req.getIdUserComanda())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));

        comanda.setDentista(dentistaRepo.findById(req.getIdDentistaComanda())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado")));

        List<Servico> servicos = servicoRepo.findAllById(req.getServicos());
        comanda.setServicos(servicos);

        return toResponse(comandaRepo.save(comanda));
    }

    public ComandaResponse buscarPorId(Long id) {
        return comandaRepo.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
    }

    public List<ComandaResponse> listarTodos() {
        return comandaRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ComandaResponse atualizar(Long id, ComandaRequest req) {
        Comanda c = comandaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));

        c.setDescricao(req.getDescricaoComanda());
        c.setValor(req.getValorComanda());
        c.setUsuario(usuarioRepo.findById(req.getIdUserComanda()).orElseThrow());
        c.setDentista(dentistaRepo.findById(req.getIdDentistaComanda()).orElseThrow());
        c.setServicos(servicoRepo.findAllById(req.getServicos()));

        return toResponse(comandaRepo.save(c));
    }

    public void deletar(Long id) {
        Comanda c = comandaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada"));
        comandaRepo.delete(c);
    }

    private ComandaResponse toResponse(Comanda c) {
        ComandaResponse r = new ComandaResponse();
        r.setId(c.getId());
        r.setIdUserComanda(c.getUsuario().getIdUser());
        r.setIdDentistaComanda(c.getDentista().getId());
        r.setValorComanda(c.getValor());
        r.setDescricaoComanda(c.getDescricao());
        r.setStatusComanda(c.getStatus());
        r.setServicos(c.getServicos().stream()
                .map(s -> new ServicoResumo(s.getNome(), s.getValor()))
                .collect(Collectors.toList()));
        return r;
    }
}
