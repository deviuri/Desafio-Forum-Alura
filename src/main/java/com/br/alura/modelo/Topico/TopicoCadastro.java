package com.br.alura.modelo.Topico;

import com.br.alura.modelo.Autor.CadastroUsuario;
import com.br.alura.modelo.Curso.CursoCadastro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoCadastro(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull StatusTopico status,
        @NotNull CadastroUsuario autor,
        @NotNull CursoCadastro curso) {

}
