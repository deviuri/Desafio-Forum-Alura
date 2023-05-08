package com.br.alura.repository;

import com.br.alura.modelo.Curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findAllBy(Pageable page);
}
