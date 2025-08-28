package com.fluxy.service.modal;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "nome_user", nullable = false, length = 100)
    private String nomeUser;

    @Column(name = "email_user", nullable = false, unique = true, length = 100)
    private String emailUser;

    @Column(name = "senha_user", nullable = false, length = 255)
    private String senhaUser;

    @Column(name = "telefone_user", length = 20)
    private String telefoneUser;

    @Column(name = "cpf_user", length = 14)
    private String cpfUser;

    @Column(name = "cnpj_user", length = 18)
    private String cnpjUser;

    @Column(name = "nome_empresa_user", nullable = false, length = 100)
    private String nomeEmpresaUser;

    @Column(name = "status_user", length = 50)
    private String statusUser;
}
