package br.com.napoleao.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.napoleao.projeto.dto.PerfilDTO;
import br.com.napoleao.projeto.entity.PerfilEntity;
import br.com.napoleao.projeto.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	public List<PerfilDTO> listAll() {
		List<PerfilEntity> perfis = perfilRepository.findAll();
		return perfis.stream().map(PerfilDTO::new).toList();
	}

	public void insert(PerfilDTO perfil) {
		PerfilEntity perfilEntity = new PerfilEntity(perfil);
		perfilRepository.save(perfilEntity);
	}

	public PerfilDTO update(PerfilDTO perfil) {
		PerfilEntity perfilEntity = new PerfilEntity(perfil);
		return new PerfilDTO(perfilRepository.save(perfilEntity));
	}

	public void delete(Long id) {
		PerfilEntity perfil = perfilRepository.findById(id).get();
		perfilRepository.delete(perfil);
	}

	public PerfilDTO findById(Long id) {
		return new PerfilDTO(perfilRepository.findById(id).get());
	}

}
