package com.br.alura.domain.Autor;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;

    public Usuario(CadastroUsuario autor) {
        this.ativo = true;
        this.nome = autor.nome();
        this.email = autor.email();
        this.senha = autor.senha();
    }

    public void desativar(){
        this.ativo = false;
    }

}
