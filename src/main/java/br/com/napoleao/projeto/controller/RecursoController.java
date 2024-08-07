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

import br.com.napoleao.projeto.dto.RecursoDTO;
import br.com.napoleao.projeto.service.RecursoService;

@RestController
@RequestMapping(value = "/recurso")
@CrossOrigin
public class RecursoController {

	@Autowired
	private RecursoService recursoService;
	
	@GetMapping
	public List<RecursoDTO> ListAll(){
		return recursoService.ListAll();
	}
	
	@PostMapping
	public void insert(@RequestBody RecursoDTO recurso) {
		recursoService.insert(recurso);
	}

	@PutMapping
	public RecursoDTO update(@RequestBody RecursoDTO recurso) {
		return recursoService.update(recurso);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable("id") Long id) {
		recursoService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
