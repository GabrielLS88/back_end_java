package com.fluxy.service.dto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ComandaResponse {
    private Long id;
    private Long idUserComanda;
    private Long idDentistaComanda;
    private BigDecimal valorComanda;
    private String descricaoComanda;
    private String statusComanda;
    private List<ServicoResumo> servicos;
}

