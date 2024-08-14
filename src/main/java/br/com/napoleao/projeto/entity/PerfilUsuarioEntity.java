package br.com.napoleao.projeto.entity;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.dto.PerfilUsuarioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_perfil_usuario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class PerfilUsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private PerfilEntity perfil;
	
	
	public PerfilUsuarioEntity(PerfilUsuarioDTO perfilUsuario) {
		BeanUtils.copyProperties(perfilUsuario, this);
		
		if(perfilUsuario != null && perfilUsuario.getUsuario() != null) {
			this.usuario = new UsuarioEntity(perfilUsuario.getUsuario());
		}
		
		if(perfilUsuario != null && perfilUsuario.getPerfil() != null) {
			this.perfil = new PerfilEntity(perfilUsuario.getPerfil());
		}
	}
	
}
