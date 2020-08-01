package com.crash.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crash.domain.Detalhes;
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

}
