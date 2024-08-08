package br.com.napoleao.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.napoleao.projeto.dto.PermissaoPerfilDTO;
import br.com.napoleao.projeto.entity.PermissaoPerfilEntity;
import br.com.napoleao.projeto.repository.PermissaoPerfilRepository;

@Service
public class PermissaoPerfilService {
	
	@Autowired
	private  PermissaoPerfilRepository permissaoPerfilRepository;

	public List<PermissaoPerfilDTO> listAll() {
		List<PermissaoPerfilEntity> permissoesPerfis = permissaoPerfilRepository.findAll();
		return permissoesPerfis.stream().map(PermissaoPerfilDTO::new).toList();
	}

	public void insert(PermissaoPerfilDTO permissaoPerfil) {
		PermissaoPerfilEntity permissaoUsuarioEntity = new PermissaoPerfilEntity(permissaoPerfil);
		permissaoPerfilRepository.save(permissaoUsuarioEntity);
	}
	
	public PermissaoPerfilDTO update(PermissaoPerfilDTO permissaoPerfil) {
		PermissaoPerfilEntity permissaoPerfilEntity = new PermissaoPerfilEntity(permissaoPerfil);
		return new PermissaoPerfilDTO(permissaoPerfilRepository.save(permissaoPerfilEntity));
	}


	public void delete(Long id) {
		PermissaoPerfilEntity permissaoPerfil = permissaoPerfilRepository.findById(id).get();
		permissaoPerfilRepository.delete(permissaoPerfil);
	}

	public PermissaoPerfilDTO findById(Long id) {
		return new PermissaoPerfilDTO(permissaoPerfilRepository.findById(id).get());
	}
}
