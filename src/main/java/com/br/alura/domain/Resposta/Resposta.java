package com.br.alura.domain.Resposta;


import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "resposta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	@ManyToOne(fetch = FetchType.LAZY)
	private Topico topico;
	private LocalDateTime dataCriacao;
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	private Boolean solucao = false;

	public Resposta(CadastroResposta resposta) {
		this.mensagem = resposta.mensagem();
		this.topico = resposta.topico();
		this.dataCriacao = LocalDateTime.now();
		this.usuario = resposta.usuario();
		this.solucao = resposta.solucao();
	}
}
