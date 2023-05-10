package com.br.alura.domain.Curso;

import jakarta.validation.constraints.NotBlank;

public record CursoCadastro(
        @NotBlank String nome,
        @NotBlank String categoria) {

}
