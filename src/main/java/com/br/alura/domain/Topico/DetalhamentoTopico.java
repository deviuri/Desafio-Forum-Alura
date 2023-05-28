package com.br.alura.domain.Topico;

import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Curso.Curso;

import java.time.LocalDateTime;

public record DetalhamentoTopico(Long id, String titulo, String mensagem, StatusTopico status, LocalDateTime data_criacao, Usuario usuario, Curso curso) {
    public DetalhamentoTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensagem(), topico.getStatus(), topico.getDataCriacao(), topico.getUsuario(), topico.getCurso());
    }

}
