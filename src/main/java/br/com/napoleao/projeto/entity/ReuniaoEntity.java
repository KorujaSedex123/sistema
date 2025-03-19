package br.com.napoleao.projeto.entity;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.dto.Reuniao2DTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

	@Column(nullable = false)
	private String descricao;

	@Column(name = "data_reuniao", nullable = false)
	private LocalDateTime dataReuniao;
	
	@Transient
	private String data;

	public ReuniaoEntity(Reuniao2DTO reuniao) {
		BeanUtils.copyProperties(reuniao, this);
	}

}
