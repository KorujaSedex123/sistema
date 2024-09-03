package br.com.napoleao.projeto.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.napoleao.projeto.entity.ReuniaoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReuniaoDTO {

	private Long id;
	private String descricao;
	
	  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date dataReuniao;
	
	public ReuniaoDTO(ReuniaoEntity reuniao) {
		BeanUtils.copyProperties(reuniao, this);
	}
	
}
