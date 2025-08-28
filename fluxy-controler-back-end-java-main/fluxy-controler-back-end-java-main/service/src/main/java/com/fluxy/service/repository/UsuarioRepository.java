package com.fluxy.service.repository;

import com.fluxy.service.modal.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailUser(String emailUser);
}
