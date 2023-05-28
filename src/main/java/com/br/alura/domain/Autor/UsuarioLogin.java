package com.br.alura.domain.Autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLogin(@Email @NotBlank String email, @NotBlank String senha) {


}
