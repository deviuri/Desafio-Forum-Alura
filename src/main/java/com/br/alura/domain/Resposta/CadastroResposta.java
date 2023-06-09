package com.br.alura.domain.Resposta;

import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Topico.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroResposta (
        @NotBlank
        String mensagem,
        @NotNull
        Topico topico,
        @NotNull
        Usuario usuario,
        Boolean solucao
) {
}
