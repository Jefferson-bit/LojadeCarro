package com.crash.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crash.domain.Detalhes;
import com.crash.repositories.DetalhesRepository;
import com.crash.service.exceptions.ResourceNotFoundException;

@Service
public class DetalhesService {
	
	@Autowired
	private DetalhesRepository repository;
	
	public Detalhes findById(Integer id) {
		Optional<Detalhes> detalhes = repository.findById(id);
		return detalhes.orElseThrow(() -> new ResourceNotFoundException(id));
	}

}
