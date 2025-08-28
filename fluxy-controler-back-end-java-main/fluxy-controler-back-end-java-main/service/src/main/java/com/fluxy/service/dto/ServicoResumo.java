package com.fluxy.service.dto;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
@Data
public class ServicoResumo {
    private String nome;
    private BigDecimal valor;

    public ServicoResumo(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }
}

