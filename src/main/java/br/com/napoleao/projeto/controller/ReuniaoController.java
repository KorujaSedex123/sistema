package br.com.napoleao.projeto.controller;

import java.text.ParseException;
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

import br.com.napoleao.projeto.dto.Reuniao2DTO;
import br.com.napoleao.projeto.service.ReuniaoService;

@RestController
@RequestMapping(value = "/reuniao")
@CrossOrigin
public class ReuniaoController {

	@Autowired
	private ReuniaoService reuniaoService;

	@GetMapping
	public List<Reuniao2DTO> ListAll() {
		return reuniaoService.listAll();
	}

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Reuniao2DTO reuniaoDTO) throws ParseException {
		try {
			reuniaoService.insert(reuniaoDTO);
			return ResponseEntity.ok("Reunião cadastrada com sucesso!");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); // Retorna erro 400 com a mensagem
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro ao cadastrar reunião."); // Erro genérico
		}
	}

	@PutMapping
	public Reuniao2DTO update(@RequestBody Reuniao2DTO reuniaoDTO) {
		return reuniaoService.update(reuniaoDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		reuniaoService.delete(id);
		return ResponseEntity.ok().build();
	}

}
