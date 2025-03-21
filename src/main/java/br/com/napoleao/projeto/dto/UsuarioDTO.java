package br.com.napoleao.projeto.dto;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.entity.UsuarioEntity;
import br.com.napoleao.projeto.entity.enums.TipoSituacaoUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private TipoSituacaoUsuario situacao;

	public UsuarioDTO(UsuarioEntity usuario) {
		BeanUtils.copyProperties(usuario, this);
	}
	
}
