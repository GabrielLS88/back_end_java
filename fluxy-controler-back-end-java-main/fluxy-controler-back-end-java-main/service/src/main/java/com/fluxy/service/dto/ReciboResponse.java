package com.fluxy.service.dto;

import lombok.Data;

@Data
public class ReciboResponse {

    private Long id;
    private Long idUserResponsavel;
    private String dataRecibo;
    private String valorRecibo;
    private String descricaoRecibo;

    private String nomePagadorRecibo;
    private String cpfPagador;
    private String cnpjPagador;

    private String nomeEmissorRecibo;
    private String cpfEmissorRecibo;
    private String cnpjEmissorRecibo;

    private String statusRecibo;
}

