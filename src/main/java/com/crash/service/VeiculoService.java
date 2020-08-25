package com.crash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crash.domain.Categoria;
import com.crash.domain.Detalhes;
import com.crash.domain.Veiculo;
import com.crash.domain.dto.VeiculoNewDTO;
import com.crash.repositories.CategoriaRepository;
import com.crash.repositories.DetalhesRepository;
import com.crash.repositories.VeiculoRepository;
import com.crash.service.exceptions.ResourceNotFoundException;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	@Autowired
	private DetalhesRepository detRepository;
	
	@Autowired
	private CategoriaRepository catRepository;
	
	public Veiculo findById(Integer id) {
		Optional<Veiculo> cat = repository.findById(id);
		return cat.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Veiculo> findAll() {
		return repository.findAll();
	}
	
	@Transactional
	public Veiculo insert(Veiculo obj) {
		obj.setId(null);
		obj = repository.save(obj);
		detRepository.save(obj.getDetalhes());
		return obj; 
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Veiculo update(Veiculo obj, Integer id) {
		Veiculo entity = repository.getOne(id);
		detRepository.save(obj.getDetalhes());
		updateData(entity, obj);
		return repository.save(entity);	  
	}
	
	public void updateData(Veiculo objNew, Veiculo obj) {
		objNew.setModelo(obj.getModelo());
		objNew.setPreco(obj.getPreco());
		objNew.setAno(obj.getAno());
		objNew.setTipoVeiculo(obj.getTipoVeiculo());
		objNew.setDetalhes(obj.getDetalhes());
	}
	
	public Veiculo fromNewDto(VeiculoNewDTO objDto) {
		Categoria cat = new Categoria(objDto.getCategoriaId(), objDto.getMarca());
		Detalhes det = new Detalhes(null, objDto.getCor(), objDto.getKmRodado(), objDto.getPortas(), objDto.getCambio(), objDto.getInformacoes());
		Veiculo vei = new Veiculo(null, objDto.getModelo(), objDto.getAno(), objDto.getPreco(), objDto.getTipoVeiculo(), cat, det);
		return vei;
	}
	
	public Veiculo fromDTO(VeiculoNewDTO objDto) {
		Detalhes det = new Detalhes(objDto.getDetalhesId(), objDto.getCor(), objDto.getKmRodado(), objDto.getPortas(), objDto.getCambio(), objDto.getInformacoes());
		return new Veiculo(objDto.getId(), objDto.getModelo(), objDto.getAno() ,objDto.getPreco(), objDto.getTipoVeiculo(), null, det); 
	}

	public Page<Veiculo> search(List<Integer>ids, String modelo, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pgRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = catRepository.findAllById(ids);
		
		return repository.findDistinctByModeloIgnoreCaseContainingAndCategoriasIn(modelo, categorias, pgRequest);
	}
}
