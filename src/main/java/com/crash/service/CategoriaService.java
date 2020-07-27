package com.crash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crash.domain.Categoria;
import com.crash.domain.dto.CategoriaDTO;
import com.crash.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> cat = repository.findById(id);
		return cat.orElseThrow();
	}
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getMarca());
	}
}
