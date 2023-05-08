package com.br.alura.modelo.Curso;

import jakarta.validation.constraints.NotBlank;

public record CursoCadastro(
        @NotBlank String nome,
        @NotBlank String categoria) {

}
