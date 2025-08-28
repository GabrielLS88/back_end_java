package com.fluxy.service.repository;

import com.fluxy.service.modal.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioDadosRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmailUser(String emailUser);
}
