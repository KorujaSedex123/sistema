package br.com.napoleao.projeto.service;

import java.text.ParseException;
import java.time.LocalDateTime;
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

	public List<Reuniao2DTO> listAll() {
		List<ReuniaoEntity> perfis = reuniaoRepository.findAll();
		return perfis.stream().map(Reuniao2DTO::new).toList();
	}

	public void insert(Reuniao2DTO reuniaoDTO) throws ParseException {

		reuniaoDTO.converterDataParaLocalDateTime();
		if (reuniaoDTO.getDataReuniao() == null || reuniaoDTO.getDataReuniao().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reunião deve ser marcada em uma data valida.");
        }

		ReuniaoEntity reuniaoEntity = new ReuniaoEntity(reuniaoDTO);
		reuniaoRepository.save(reuniaoEntity);
	}

	public Reuniao2DTO update(Reuniao2DTO reuniaoDTO) {
		ReuniaoEntity reuniaoEntity = new ReuniaoEntity(reuniaoDTO);
		return new Reuniao2DTO(reuniaoRepository.save(reuniaoEntity));
	}

	public void delete(Long id) {
		ReuniaoEntity perfil = reuniaoRepository.findById(id).get();
		reuniaoRepository.delete(perfil);
	}

	public ReuniaoDTO findById(Long id) {
		return new ReuniaoDTO(reuniaoRepository.findById(id).get());
	}

}
