package br.com.napoleao.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.napoleao.projeto.dto.ConfirmarPresencaDTO;
import br.com.napoleao.projeto.entity.ConfirmarPresencaEntity;
import br.com.napoleao.projeto.repository.ConfirmaPresencaRepository;

@Service
public class ConfirmaPresencaService {
	
	@Autowired
	private ConfirmaPresencaRepository confirmaPresencaRepository;

	public List<ConfirmarPresencaDTO> listAll() {
		List<ConfirmarPresencaEntity> reuniões = confirmaPresencaRepository.findAll();
		return reuniões.stream().map(ConfirmarPresencaDTO::new).toList();
	}

	public void insert(ConfirmarPresencaDTO confirmar) {
		ConfirmarPresencaEntity perfilUsuarioEntity = new ConfirmarPresencaEntity(confirmar);
		confirmaPresencaRepository.save(perfilUsuarioEntity);
	}
	
//	public ConfirmarPresencaDTO update(ConfirmarPresencaDTO perfilUsuario) {
//		PerfilUsuarioEntity perfilUsuarioEntity = new PerfilUsuarioEntity(perfilUsuario);
//		return new PerfilUsuarioDTO(perfilUsuarioRepository.save(perfilUsuarioEntity));
//	}


	public void delete(Long id) {
		ConfirmarPresencaEntity perfilUsuario = confirmaPresencaRepository.findById(id).get();
		confirmaPresencaRepository.delete(perfilUsuario);
	}

//	public ConfirmarPresencaDTO findById(Long id) {
//		return new PerfilUsuarioDTO(perfilUsuarioRepository.findById(id).get());
//	}
}
