package br.com.napoleao.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.napoleao.projeto.dto.UsuarioDTO;
import br.com.napoleao.projeto.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<UsuarioDTO> listAll() {
		return usuarioService.listAll();
	}

	@PostMapping
	public void insert(@RequestBody UsuarioDTO usuario) {
		usuarioService.insert(usuario);
	}

	@PutMapping
	public UsuarioDTO update(@RequestBody UsuarioDTO usuario) {
		return usuarioService.update(usuario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		usuarioService.delete(id);
		return ResponseEntity.ok().build();
	}

}
