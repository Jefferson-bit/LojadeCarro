package com.crash.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.crash.domain.Categoria;
import com.crash.domain.Cliente;
import com.crash.repositories.CategoriaRepository;
import com.crash.repositories.ClienteRepository;

@Configuration
@Profile("test")

public class TestConfig implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepository;
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Toyota");
		Categoria cat2 = new Categoria(null, "Ford");
		Categoria cat3 = new Categoria(null, "Chevrolet");
		Categoria cat4 = new Categoria(null, "Citroen");
		Categoria cat5 = new Categoria(null, "Honda");
		
		catRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5));
		
		Cliente cli1 = new Cliente(null, "Crash", "crashnight089@gmail.com", "123");
		Cliente cli2 = new Cliente(null, "Alex", "alex@gmail.com", "123");
		cliRepository.saveAll(Arrays.asList(cli1,cli2));
	}

}
