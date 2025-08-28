package com.fluxy.service.dto;

import lombok.Data;

@Data
public class ReciboRequest {
    private Long idUserResponsavel;
    private String dataRecibo; // formato: dd/MM/yyyy
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

