package com.fluxy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // cria construtor com todos os argumentos
@NoArgsConstructor  // cria construtor vazio
public class UsuarioResponse {
        private String nomeUser;
        private String emailUser;
        private String telefoneUser;
        private String cpfUser;
        private String cnpjUser;
        private String nomeEmpresaUser;
}
