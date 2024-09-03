package br.com.napoleao.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void insert(ReuniaoDTO reuniaoDTO) {
		ReuniaoEntity reuniaoEntity = new ReuniaoEntity(reuniaoDTO);
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
