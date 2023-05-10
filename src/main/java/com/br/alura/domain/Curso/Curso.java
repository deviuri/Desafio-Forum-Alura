package com.br.alura.domain.Curso;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "curso")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public Curso(CursoCadastro curso) {
        this.nome = curso.nome();
        this.categoria = curso.categoria();
    }
}
