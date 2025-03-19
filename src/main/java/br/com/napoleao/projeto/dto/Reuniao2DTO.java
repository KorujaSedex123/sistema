package br.com.napoleao.projeto.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.napoleao.projeto.entity.ReuniaoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Reuniao2DTO {

	private Long id;
	private String descricao;
	private String data;
	
	private LocalDateTime dataReuniao;
	
	public void converterDataParaLocalDateTime() {
        if (this.data != null && !this.data.isEmpty()) {
            // Converte diretamente para LocalDateTime
            this.dataReuniao = LocalDateTime.parse(this.data);
        } else {
            this.dataReuniao = null; // Ou trate o caso em que a string está vazia/nula
        }
    }
	public Reuniao2DTO(ReuniaoEntity reuniao) {
		BeanUtils.copyProperties(reuniao, this);
	}
	
	
}
