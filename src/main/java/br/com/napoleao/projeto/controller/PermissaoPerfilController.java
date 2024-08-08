package br.com.napoleao.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.napoleao.projeto.dto.PermissaoPerfilDTO;
import br.com.napoleao.projeto.service.PermissaoPerfilService;

@RestController
@RequestMapping(value = "/permissao-perfil")
@CrossOrigin
public class PermissaoPerfilController {

	@Autowired
	private PermissaoPerfilService permissaoPerfilService;
	
	@GetMapping
	public List<PermissaoPerfilDTO> ListAll(){
		return permissaoPerfilService.listAll();
	}
	
	@PostMapping
	public void insert(@RequestBody PermissaoPerfilDTO permissaoPerfil) {
		permissaoPerfilService.insert(permissaoPerfil);
	}
	
	@PutMapping
	public PermissaoPerfilDTO update(@RequestBody PermissaoPerfilDTO permissaoPerfil) {
		return permissaoPerfilService.update(permissaoPerfil);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		permissaoPerfilService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
