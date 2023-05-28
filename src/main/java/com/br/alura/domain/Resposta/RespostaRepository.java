package com.br.alura.domain.Resposta;

import com.br.alura.domain.Resposta.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Page<Resposta> findAllBy(Pageable paginacao);
}
