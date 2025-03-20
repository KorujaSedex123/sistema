package br.com.napoleao.projeto.dto;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.entity.ConfirmarPresencaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmarPresencaDTO {
	
	private Long id;
	private UsuarioDTO usuario;
	private Reuniao2DTO reuniao;
	
	
	public ConfirmarPresencaDTO(ConfirmarPresencaEntity confiramPresenca) {
		BeanUtils.copyProperties(confiramPresenca, this);
		if(confiramPresenca != null && confiramPresenca.getUsuario() != null) {
			this.usuario = new UsuarioDTO(confiramPresenca.getUsuario());
		}
		
		if(confiramPresenca != null && confiramPresenca.getReuniao() != null) {
			this.reuniao = new Reuniao2DTO(confiramPresenca.getReuniao());
		}
	}

}
