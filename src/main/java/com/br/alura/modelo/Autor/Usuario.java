package com.br.alura.modelo.Autor;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(CadastroUsuario autor) {
        this.nome = autor.nome();
        this.email = autor.email();
        this.senha = autor.senha();
    }

}
