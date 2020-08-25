package com.crash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.crash.domain.Detalhes;
import com.crash.domain.Veiculo;
import com.crash.repositories.DetalhesRepository;
import com.crash.repositories.VeiculoRepository;
import com.crash.service.exceptions.ResourceNotFoundException;


@Service
public class DetalhesService {
	
	@Autowired
	private DetalhesRepository repository;
	
	@Autowired
	private VeiculoRepository veiRepository;
	
	public Detalhes findById(Integer id) {
		Optional<Detalhes> detalhes = repository.findById(id);
		return detalhes.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Detalhes> findAll(){
		return repository.findAll();
	}
	
	public Page<Detalhes> search(List<Integer> ids, Integer page, Integer linesPerPage,String orderBy, String direction ){
		PageRequest pgRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Veiculo> veiculos = veiRepository.findAllById(ids);
		return repository.search(veiculos, pgRequest);
	}
	
}
