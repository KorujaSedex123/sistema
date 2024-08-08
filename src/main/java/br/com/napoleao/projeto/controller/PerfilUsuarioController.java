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

import br.com.napoleao.projeto.dto.PerfilUsuarioDTO;
import br.com.napoleao.projeto.service.PerfilUsuarioService;

@RestController
@RequestMapping(value = "/perfil-usuario")
@CrossOrigin
public class PerfilUsuarioController {

	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@GetMapping
	public List<PerfilUsuarioDTO> ListAll(){
		return perfilUsuarioService.listAll();
	}
	
	@PostMapping
	public void insert(@RequestBody PerfilUsuarioDTO perfilUsuario) {
		perfilUsuarioService.insert(perfilUsuario);
	}
	
	@PutMapping
	public PerfilUsuarioDTO update(@RequestBody PerfilUsuarioDTO perfilUsuario) {
		return perfilUsuarioService.update(perfilUsuario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		perfilUsuarioService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
