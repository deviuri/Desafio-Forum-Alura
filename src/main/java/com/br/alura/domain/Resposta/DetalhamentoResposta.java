package com.br.alura.domain.Resposta;

import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Topico.Topico;

public record DetalhamentoResposta(Long id, String mensagem, Topico topico, Usuario usuario, Boolean solucao) {

    public DetalhamentoResposta(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico(), resposta.getUsuario(), resposta.getSolucao());
    }
}
