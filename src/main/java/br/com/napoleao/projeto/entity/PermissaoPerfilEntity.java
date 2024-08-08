package br.com.napoleao.projeto.entity;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.dto.PermissaoPerfilDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_permissao_perfil")
@Getter
@Setter
@NoArgsConstructor
public class PermissaoPerfilEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_recurso")
	private RecursoEntity recurso;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private PerfilEntity perfil;
	
	
	public PermissaoPerfilEntity(PermissaoPerfilDTO permissaoPerfil) {
		BeanUtils.copyProperties(permissaoPerfil, this);
		if(permissaoPerfil != null && permissaoPerfil.getRecurso() != null) {
			this.recurso = new RecursoEntity(permissaoPerfil.getRecurso());
		}
		
		if(permissaoPerfil != null && permissaoPerfil.getPerfil() != null) {
			this.perfil = new PerfilEntity(permissaoPerfil.getPerfil());
		}
	}
	
}
