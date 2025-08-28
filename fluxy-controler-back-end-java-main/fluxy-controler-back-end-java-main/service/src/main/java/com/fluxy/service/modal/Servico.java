package com.fluxy.service.modal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "servicos")
@Data
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user_responsavel")
    private Usuario usuarioResponsavel;

    @Column(name = "nome_servico", nullable = false)
    private String nome;

    @Column(name = "valor_servico", nullable = false)
    private BigDecimal valor;

    @Column(name = "status_servico")
    private String status = "ATIVO";
}

