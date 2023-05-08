package com.br.alura.modelo.Autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CadastroUsuario(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String senha) {
}
