package com.br.alura.domain.Topico;

import com.br.alura.domain.Autor.CadastroUsuario;
import com.br.alura.domain.Curso.CursoCadastro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TopicoCadastro(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull StatusTopico status,
        @NotNull CadastroUsuario usuario,
        @NotNull CursoCadastro curso
        ) {

}
