package br.com.napoleao.projeto.entity;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.dto.ConfirmarPresencaDTO;
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
@Table(name = "tb_confirmar_presenca")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class ConfirmarPresencaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private UsuarioEntity usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_reuniao", nullable = false)
	private ReuniaoEntity reuniao;
	
	
	public ConfirmarPresencaEntity(ConfirmarPresencaDTO confirmarPresenca) {
		BeanUtils.copyProperties(confirmarPresenca, this);
		
		if(confirmarPresenca != null && confirmarPresenca.getUsuario() != null) {
			this.usuario = new UsuarioEntity(confirmarPresenca.getUsuario());
		}
		
		if(confirmarPresenca != null && confirmarPresenca.getReuniao() != null) {
			this.reuniao = new ReuniaoEntity(confirmarPresenca.getReuniao());
		}
		
		
	}
	
}
