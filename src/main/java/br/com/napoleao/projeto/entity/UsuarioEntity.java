package br.com.napoleao.projeto.entity;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.dto.UsuarioDTO;
import br.com.napoleao.projeto.entity.enums.TipoSituacaoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoSituacaoUsuario situacao;

	public UsuarioEntity(UsuarioDTO usuario) {
		BeanUtils.copyProperties(usuario, this);
	}

}
