package com.br.alura.domain.Curso;

import com.br.alura.domain.Curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findAllBy(Pageable page);
}
