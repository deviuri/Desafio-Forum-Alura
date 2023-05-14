package com.br.alura.repository;

import com.br.alura.domain.Autor.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAllBy(Pageable page);

    Usuario findByEmail(String email);
}
