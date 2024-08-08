package br.com.napoleao.projeto.dto;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.entity.PermissaoPerfilEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissaoPerfilDTO {
	
	private Long id;
	private RecursoDTO recurso;
	private PerfilDTO perfil;
	
	
	public PermissaoPerfilDTO(PermissaoPerfilEntity permissaoPerfil) {
		BeanUtils.copyProperties(permissaoPerfil, this);
		if(permissaoPerfil != null && permissaoPerfil.getRecurso() != null) {
			this.recurso = new RecursoDTO(permissaoPerfil.getRecurso());
		}
		
		if(permissaoPerfil != null && permissaoPerfil.getPerfil() != null) {
			this.perfil = new PerfilDTO(permissaoPerfil.getPerfil());
		}
	}

}
