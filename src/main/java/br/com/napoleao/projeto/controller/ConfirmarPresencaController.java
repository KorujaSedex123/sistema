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

import br.com.napoleao.projeto.dto.ConfirmarPresencaDTO;
import br.com.napoleao.projeto.service.ConfirmaPresencaService;


@RestController
@RequestMapping(value = "/confirma-presenca")
@CrossOrigin
public class ConfirmarPresencaController {

	@Autowired
	private ConfirmaPresencaService confirmaPresencaService;
	
	@GetMapping
	public List<ConfirmarPresencaDTO> ListAll(){
		return confirmaPresencaService.listAll();
	}
	
	@PostMapping
	public void insert(@RequestBody ConfirmarPresencaDTO confirmaPresenca) {
		confirmaPresencaService.insert(confirmaPresenca);
	}
	
//	@PutMapping
//	public ConfirmarPresencaDTO update(@RequestBody ConfirmarPresencaDTO perfilUsuario) {
//		return confirmaPresencaService.update(perfilUsuario);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		confirmaPresencaService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
