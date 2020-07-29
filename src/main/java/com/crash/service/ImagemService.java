package com.crash.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crash.domain.Imagem;
import com.crash.repositories.ImagemRepository;
import com.crash.service.exceptions.ResourceNotFoundException;

@Service
public class ImagemService {
	
	@Autowired
	private ImagemRepository repository;
	
	public Imagem findById(Integer id) {
		Optional<Imagem> img = repository.findById(id);
		return img.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Imagem insert(Imagem img) {
		img.setId(null);
		return repository.save(img);
	}
}
