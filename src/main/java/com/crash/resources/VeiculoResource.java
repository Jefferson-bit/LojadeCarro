package com.crash.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crash.domain.Veiculo;
import com.crash.domain.dto.VeiculoDTO;
import com.crash.domain.dto.VeiculoNewDTO;
import com.crash.resources.utils.URL;
import com.crash.service.VeiculoService;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoResource {
	
	@Autowired
	private VeiculoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Veiculo> findById(@PathVariable Integer id) {
		Veiculo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<VeiculoDTO>> findAll(){
		List<Veiculo> list = service.findAll();
		List<VeiculoDTO> listDto = list.stream().map(obj -> new VeiculoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<VeiculoDTO>> findPage(
			@RequestParam(value = "modelo", defaultValue = "") String modelo,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "modelo") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		List<Integer> ids = URL.decodeItens(categorias);
		String nomeDecode = URL.decodeParam(modelo);
		Page<Veiculo> list = service.search(ids, nomeDecode, page, linesPerPage, orderBy, direction);
		Page<VeiculoDTO> listDto = list.map(obj -> new VeiculoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody VeiculoNewDTO objDto){
	    Veiculo obj = service.fromNewDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VeiculoNewDTO> update(@Valid @PathVariable Integer id, @RequestBody VeiculoNewDTO objDto){
		Veiculo entity = service.fromDTO(objDto);
		entity.setId(id);
		entity = service.update(entity, id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
