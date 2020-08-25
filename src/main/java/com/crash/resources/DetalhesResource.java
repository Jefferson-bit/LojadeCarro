package com.crash.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crash.domain.Detalhes;
import com.crash.resources.utils.URL;
import com.crash.service.DetalhesService;

@RestController
@RequestMapping(value = "/detalhes")
public class DetalhesResource {

	@Autowired
	private DetalhesService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Detalhes> findById( @PathVariable Integer id) {	
		Detalhes detalhes = service.findById(id);
		return ResponseEntity.ok().body(detalhes);
	}
	
	@GetMapping
	public ResponseEntity<List<Detalhes>> findAll(){
		List<Detalhes> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Detalhes>> page(
		@RequestParam(value = "veiculos", defaultValue = "")String veiculos,
		@RequestParam(value = "page", defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
		@RequestParam(value = "direction", defaultValue = "ASC")String direction,
		@RequestParam(value = "orderBy", defaultValue = "cor")String orderBy){
		List<Integer> ids = URL.decodeItens(veiculos);
		Page<Detalhes> pageDetalhes = service.search(ids, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(pageDetalhes);
	}
}
