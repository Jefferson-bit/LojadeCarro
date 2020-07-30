package com.crash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.crash.domain.Categoria;
import com.crash.domain.Veiculo;
import com.crash.domain.dto.VeiculoDTO;
import com.crash.domain.dto.VeiculoNewDTO;
import com.crash.repositories.VeiculoRepository;
import com.crash.service.exceptions.ResourceNotFoundException;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	public Veiculo findById(Integer id) {
		Optional<Veiculo> cat = repository.findById(id);
		return cat.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Veiculo> findAll() {
		return repository.findAll();
	}
	
	public Veiculo insert(Veiculo obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Veiculo update(Veiculo obj, Integer id) {
		Veiculo entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	public void updateData(Veiculo objNew, Veiculo obj) {
		objNew.setModelo(obj.getModelo());
		objNew.setPreco(obj.getPreco());
		objNew.setAno(obj.getAno());
	}
	
	public Veiculo fromNewDto(VeiculoNewDTO objDto) {
		Categoria cat = new Categoria(objDto.getCategoriaId(), null);
		Veiculo vei = new Veiculo(null, objDto.getModelo(), objDto.getAno(), objDto.getPreco(), cat);
		return vei;
	}
	
	public Veiculo fromDTO(VeiculoDTO objDto) {	
		return new Veiculo(objDto.getId(), objDto.getModelo(), objDto.getAno() ,objDto.getPreco(), null);
	}
	
	public Page<Veiculo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pgRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pgRequest);
	}
}
