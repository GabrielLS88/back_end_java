package com.fluxy.service.dto;

import lombok.Data;

@Data
public class DentistaResponse {
    private Long id;
    private Long idUserResponsavel;
    private String nomeDentista;
    private String emailDentista;
    private String telefoneDentista;
    private String cpfDentista;
    private String cnpjDentista;
    private String nomeClinicaDentista;
    private String enderecoDentista;
    private String statusDentista;
}

