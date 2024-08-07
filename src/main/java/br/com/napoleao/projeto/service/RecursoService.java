package br.com.napoleao.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.napoleao.projeto.dto.RecursoDTO;
import br.com.napoleao.projeto.entity.RecursoEntity;
import br.com.napoleao.projeto.repository.RecursoRepository;

@Service
public class RecursoService {

	@Autowired
	private RecursoRepository recursoRepository;

	public List<RecursoDTO> ListAll() {
		List<RecursoEntity> recursos = recursoRepository.findAll();
		return recursos.stream().map(RecursoDTO::new).toList();
	}

	public void insert(RecursoDTO recurso) {
		RecursoEntity recursoEntity = new RecursoEntity(recurso);
		recursoRepository.save(recursoEntity);
	}

	public RecursoDTO update(RecursoDTO recurso) {
		RecursoEntity recursoEntity = new RecursoEntity(recurso);
		return new RecursoDTO(recursoRepository.save(recursoEntity));
	}

	public void delete(Long id) {
			RecursoEntity recurso = recursoRepository.findById(id).get();
			recursoRepository.delete(recurso);
	}
	
	public RecursoDTO findById(Long id) {
		return new RecursoDTO(recursoRepository.findById(id).get());
	}

}
