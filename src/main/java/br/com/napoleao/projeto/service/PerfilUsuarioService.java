package br.com.napoleao.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.napoleao.projeto.dto.PerfilUsuarioDTO;
import br.com.napoleao.projeto.entity.PerfilUsuarioEntity;
import br.com.napoleao.projeto.repository.PerfilUsuarioRepository;

@Service
public class PerfilUsuarioService {
	
	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;

	public List<PerfilUsuarioDTO> listAll() {
		List<PerfilUsuarioEntity> perfisUsuarios = perfilUsuarioRepository.findAll();
		return perfisUsuarios.stream().map(PerfilUsuarioDTO::new).toList();
	}

	public void insert(PerfilUsuarioDTO perfilUsuario) {
		PerfilUsuarioEntity perfilUsuarioEntity = new PerfilUsuarioEntity(perfilUsuario);
		perfilUsuarioRepository.save(perfilUsuarioEntity);
	}
	
	public PerfilUsuarioDTO update(PerfilUsuarioDTO perfilUsuario) {
		PerfilUsuarioEntity perfilUsuarioEntity = new PerfilUsuarioEntity(perfilUsuario);
		return new PerfilUsuarioDTO(perfilUsuarioRepository.save(perfilUsuarioEntity));
	}


	public void delete(Long id) {
		PerfilUsuarioEntity perfilUsuario = perfilUsuarioRepository.findById(id).get();
		perfilUsuarioRepository.delete(perfilUsuario);
	}

	public PerfilUsuarioDTO findById(Long id) {
		return new PerfilUsuarioDTO(perfilUsuarioRepository.findById(id).get());
	}
}
