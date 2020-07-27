package com.crash.resources;

import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crash.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@GetMapping
	public List<Categoria> find() {
		List<Categoria> list = new ArrayList<>();
		list.add(new Categoria(1, "Informatica"));
		list.add(new Categoria(2, "Serra"));
		return list;
	}
}
