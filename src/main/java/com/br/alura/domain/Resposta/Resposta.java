package com.br.alura.domain.Resposta;


import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "resposta")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	@ManyToOne
	private Topico topico;
	private LocalDateTime dataCriacao;
	@ManyToOne
	private Usuario usuario;
	private Boolean solucao = false;


}
