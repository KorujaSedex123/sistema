package br.com.napoleao.projeto.dto;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.entity.RecursoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecursoDTO {
	
	private Long id;
	private String nome;
	private String chave;
	

	
	public RecursoDTO(RecursoEntity recurso) {
		BeanUtils.copyProperties(recurso, this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoDTO other = (RecursoDTO) obj;
		return Objects.equals(id, other.id);
	}
}
