package com.fluxy.service.dto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicoRequest {
    private Long idUserResponsavel;
    private String nomeServico;
    private BigDecimal valorServico;
    private String statusServico;
}

