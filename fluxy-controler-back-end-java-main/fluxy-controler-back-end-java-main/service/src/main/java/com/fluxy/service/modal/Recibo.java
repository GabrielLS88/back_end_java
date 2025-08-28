package com.fluxy.service.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "recibos")
@Data
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recibo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user_responsavel")
    private Usuario usuarioResponsavel;

    @Column(name = "data_recibo")
    private LocalDate data;

    @Column(name = "valor_recibo")
    private BigDecimal valor;

    @Column(name = "descricao_recibo")
    private String descricao;

    @Column(name = "nome_pagador_recibo")
    private String nomePagador;

    @Column(name = "cpf_pagador")
    private String cpfPagador;

    @Column(name = "cnpj_pagador")
    private String cnpjPagador;

    @Column(name = "nome_emissor_recibo")
    private String nomeEmissor;

    @Column(name = "cpf_emissor_recibo")
    private String cpfEmissor;

    @Column(name = "cnpj_emissor_recibo")
    private String cnpjEmissor;

    @Column(name = "status_recibo")
    private String status = "ATIVO";
}
