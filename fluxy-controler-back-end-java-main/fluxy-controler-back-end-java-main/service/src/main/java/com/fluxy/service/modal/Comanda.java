package com.fluxy.service.modal;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "comanda")
@Data
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comanda")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user_comanda")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_dentista_comanda")
    private Dentista dentista;

    @Column(name = "valor_comanda")
    private BigDecimal valor;

    @Column(name = "descricao_comanda")
    private String descricao;

    @Column(name = "status_comanda")
    private String status;

    @ManyToMany
    @JoinTable(
            name = "comanda_servicos",
            joinColumns = @JoinColumn(name = "id_comanda"),
            inverseJoinColumns = @JoinColumn(name = "id_servico")
    )
    private List<Servico> servicos;
}
