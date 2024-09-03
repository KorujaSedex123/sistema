package br.com.napoleao.projeto.entity;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.dto.ReuniaoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_reuniao")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ReuniaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String descricao;

	@Column(name = "data_reuniao")
	private Date dataReuniao;

	public ReuniaoEntity(ReuniaoDTO reuniao) {
		BeanUtils.copyProperties(reuniao, this);
	}

}
