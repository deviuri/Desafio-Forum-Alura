package com.br.alura.domain.Topico;

import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Curso.Curso;
import com.br.alura.domain.Resposta.Resposta;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	@Enumerated(EnumType.STRING)
	private StatusTopico status;
	@ManyToOne
	@JoinColumn(name="autor_id")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="curso_id")
	private Curso curso;
	@OneToMany
	private List<Resposta> respostas = new ArrayList<>();
	private Boolean ativo;

	public Topico(TopicoCadastro cadastro) {
		this.ativo = true;
		this.titulo = cadastro.titulo();
		this.mensagem = cadastro.mensagem();
		this.status = cadastro.status();
		this.dataCriacao = LocalDateTime.now();
	}
	public void editarTopico(AtualizaçãoTopico topico){
		if(topico.titulo() != null){
			this.titulo = topico.titulo();
		}
		if (topico.mensagem() != null){
			this.mensagem = topico.mensagem();
		}
		if (topico.statusTopico() != null){
			this.status = topico.statusTopico();
		}

	}
	public void desativar(){
		this.status = StatusTopico.FECHADO;
		this.ativo = false;
	}

}
