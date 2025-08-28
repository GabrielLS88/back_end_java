package com.fluxy.service.service;

import com.fluxy.service.dto.ReciboRequest;
import com.fluxy.service.dto.ReciboResponse;
import com.fluxy.service.modal.Recibo;
import com.fluxy.service.modal.Usuario;
import com.fluxy.service.repository.ReciboRepository;
import com.fluxy.service.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReciboService {

    private final ReciboRepository repo;
    private final UsuarioRepository usuarioRepo;

    public ReciboService(ReciboRepository repo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }

    public ReciboResponse criar(ReciboRequest req) {
        Recibo r = new Recibo();

        Usuario u = usuarioRepo.findById(req.getIdUserResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        r.setUsuarioResponsavel(u);
        r.setData(LocalDate.parse(req.getDataRecibo(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        r.setValor(new BigDecimal(req.getValorRecibo()));
        r.setDescricao(req.getDescricaoRecibo());

        r.setNomePagador(req.getNomePagadorRecibo());
        r.setCpfPagador(req.getCpfPagador());
        r.setCnpjPagador(req.getCnpjPagador());

        r.setNomeEmissor(req.getNomeEmissorRecibo());
        r.setCpfEmissor(req.getCpfEmissorRecibo());
        r.setCnpjEmissor(req.getCnpjEmissorRecibo());

        r.setStatus(req.getStatusRecibo() != null ? req.getStatusRecibo() : "ATIVO");

        return toResponse(repo.save(r));
    }

    public List<ReciboResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ReciboResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Recibo não encontrado"));
    }

    public ReciboResponse atualizar(Long id, ReciboRequest req) {
        Recibo r = repo.findById(id).orElseThrow(() -> new RuntimeException("Recibo não encontrado"));
        Usuario u = usuarioRepo.findById(req.getIdUserResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        r.setUsuarioResponsavel(u);
        r.setData(LocalDate.parse(req.getDataRecibo(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        r.setValor(new BigDecimal(req.getValorRecibo()));
        r.setDescricao(req.getDescricaoRecibo());

        r.setNomePagador(req.getNomePagadorRecibo());
        r.setCpfPagador(req.getCpfPagador());
        r.setCnpjPagador(req.getCnpjPagador());

        r.setNomeEmissor(req.getNomeEmissorRecibo());
        r.setCpfEmissor(req.getCpfEmissorRecibo());
        r.setCnpjEmissor(req.getCnpjEmissorRecibo());

        r.setStatus(req.getStatusRecibo());

        return toResponse(repo.save(r));
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }

    private ReciboResponse toResponse(Recibo r) {
        ReciboResponse resp = new ReciboResponse();

        resp.setId(r.getId());
        resp.setIdUserResponsavel(r.getUsuarioResponsavel().getIdUser());
        resp.setDataRecibo(r.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        resp.setValorRecibo(r.getValor().toPlainString());
        resp.setDescricaoRecibo(r.getDescricao());

        resp.setNomePagadorRecibo(r.getNomePagador());
        resp.setCpfPagador(r.getCpfPagador());
        resp.setCnpjPagador(r.getCnpjPagador());

        resp.setNomeEmissorRecibo(r.getNomeEmissor());
        resp.setCpfEmissorRecibo(r.getCpfEmissor());
        resp.setCnpjEmissorRecibo(r.getCnpjEmissor());

        resp.setStatusRecibo(r.getStatus());
        return resp;
    }
}
