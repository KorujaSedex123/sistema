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

import br.com.napoleao.projeto.dto.ReuniaoDTO;
import br.com.napoleao.projeto.service.ReuniaoService;

@RestController
@RequestMapping(value = "/reuniao")
@CrossOrigin
public class ReuniaoController {

	@Autowired
	private ReuniaoService reuniaoService;
	
	@GetMapping
	public List<ReuniaoDTO> ListAll(){
		return reuniaoService.listAll();
	}
	
	@PostMapping
	public void insert(@RequestBody ReuniaoDTO reuniaoDTO) {
		reuniaoService.insert(reuniaoDTO);
	}
	
	@PutMapping
	public ReuniaoDTO update(@RequestBody ReuniaoDTO reuniaoDTO) {
		return reuniaoService.update(reuniaoDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		reuniaoService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
