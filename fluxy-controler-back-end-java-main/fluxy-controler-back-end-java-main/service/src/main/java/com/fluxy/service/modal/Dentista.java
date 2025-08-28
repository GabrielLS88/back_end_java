package com.fluxy.service.modal;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dentistas")
@Data
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dentista")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user_responsavel")
    private Usuario usuarioResponsavel;

    @Column(name = "nome_dentista")
    private String nome;

    @Column(name = "email_dentista")
    private String email;

    @Column(name = "telefone_dentista")
    private String telefone;

    @Column(name = "cpf_dentista")
    private String cpf;

    @Column(name = "cnpj_dentista")
    private String cnpj;

    @Column(name = "nome_clinica_dentista")
    private String nomeClinica;

    @Column(name = "endereco_dentista")
    private String endereco;

    @Column(name = "status_dentista")
    private String status = "ATIVO";
}
