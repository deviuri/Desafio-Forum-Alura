package com.br.alura.domain.Autor;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DetalhamentoUsuario(Long id, String nome,String email, String senha) {

    public DetalhamentoUsuario(Usuario cadastro){
        this(cadastro.getId(), cadastro.getNome(), cadastro.getEmail(), cadastro.getSenha());
    }

}
