package br.com.napoleao.projeto.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.napoleao.projeto.dto.Reuniao2DTO;
import br.com.napoleao.projeto.dto.ReuniaoDTO;
import br.com.napoleao.projeto.entity.ReuniaoEntity;
import br.com.napoleao.projeto.repository.ReuniaoRepository;

@Service
public class ReuniaoService {

	@Autowired
	private ReuniaoRepository reuniaoRepository;

	public List<ReuniaoDTO> listAll() {
		List<ReuniaoEntity> perfis = reuniaoRepository.findAll();
		return perfis.stream().map(ReuniaoDTO::new).toList();
	}

	public void insert(Reuniao2DTO reuniaoDTO) throws ParseException {
		ReuniaoDTO reuniao = new ReuniaoDTO();
		
		
		reuniao.setDescricao(reuniaoDTO.getDescricao());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date data = new java.sql.Date(format.parse(reuniaoDTO.getDataReuniao()).getTime());
	
		reuniao.setDataReuniao(data);
		ReuniaoEntity reuniaoEntity = new ReuniaoEntity(reuniao);
		reuniaoRepository.save(reuniaoEntity);
	}

	public ReuniaoDTO update(ReuniaoDTO reuniaoDTO) {
		ReuniaoEntity reuniaoEntity = new ReuniaoEntity(reuniaoDTO);
		return new ReuniaoDTO(reuniaoRepository.save(reuniaoEntity));
	}

	public void delete(Long id) {
		ReuniaoEntity perfil = reuniaoRepository.findById(id).get();
		reuniaoRepository.delete(perfil);
	}

	public ReuniaoDTO findById(Long id) {
		return new ReuniaoDTO(reuniaoRepository.findById(id).get());
	}

}
