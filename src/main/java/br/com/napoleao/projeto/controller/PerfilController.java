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

import br.com.napoleao.projeto.dto.PerfilDTO;
import br.com.napoleao.projeto.service.PerfilService;

@RestController
@RequestMapping(value = "/perfil")
@CrossOrigin
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public List<PerfilDTO> ListAll(){
		return perfilService.listAll();
	}
	
	@PostMapping
	public void insert(@RequestBody PerfilDTO perfil) {
		perfilService.insert(perfil);
	}
	
	@PutMapping
	public PerfilDTO update(@RequestBody PerfilDTO perfil) {
		return perfilService.update(perfil);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		perfilService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
