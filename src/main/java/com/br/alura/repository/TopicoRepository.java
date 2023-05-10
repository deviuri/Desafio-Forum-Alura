package com.br.alura.repository;

import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByAtivoTrue(Pageable paginacao);
    Page<Topico> findByUsuario(Usuario usuario, Pageable pageable);


    Optional<Topico> findByTitulo(String titulo);

    Optional<Topico> findByMensagem(String mensagem);

    Page<Topico> findAllByCurso_Nome(Pageable pageable, String curso);
}
