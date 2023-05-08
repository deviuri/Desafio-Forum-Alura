package com.br.alura.modelo;


import com.br.alura.modelo.Autor.Usuario;
import com.br.alura.modelo.Topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "resposta")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
		Resposta other = (Resposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
