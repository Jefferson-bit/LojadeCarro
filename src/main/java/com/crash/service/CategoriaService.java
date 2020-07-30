package com.crash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.crash.domain.Categoria;
import com.crash.domain.dto.CategoriaDTO;
import com.crash.repositories.CategoriaRepository;
import com.crash.service.exceptions.DataBaseException;
import com.crash.service.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> cat = repository.findById(id);
		return cat.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
	    findById(id);
	    try {
		repository.deleteById(id);
	    }catch(DataIntegrityViolationException e) {
	    	throw new DataBaseException("Não é possível excluir uma categoria que contenha Veiculos");
	    }
	}
	
	public Categoria update(Categoria obj, Integer id) {
		Categoria entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	public void updateData(Categoria objNew, Categoria obj) {
		objNew.setMarca(obj.getMarca());
	}
	
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getMarca());
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pgRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pgRequest);
	}
}
