package com.br.alura.modelo.Topico;

import com.br.alura.modelo.Autor.Usuario;
import com.br.alura.modelo.Curso.Curso;
import com.br.alura.modelo.Resposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
