package com.crash.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.crash.domain.Categoria;
import com.crash.domain.Cliente;
import com.crash.domain.Veiculo;
import com.crash.repositories.CategoriaRepository;
import com.crash.repositories.ClienteRepository;
import com.crash.repositories.VeiculoRepository;

@Configuration
@Profile("test")

public class TestConfig implements CommandLineRunner{

	@Autowired
	private CategoriaRepository catRepository;
	
	@Autowired
	private ClienteRepository cliRepository;
	

	@Autowired
	private VeiculoRepository veiRepository;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Toyota");
		Categoria cat2 = new Categoria(null, "Ford");
		Categoria cat3 = new Categoria(null, "Chevrolet");
		Categoria cat4 = new Categoria(null, "Citroen");
		Categoria cat5 = new Categoria(null, "Honda");

		catRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5));
		
		Veiculo vei1 = new Veiculo(null, "Corolla GLI", sdf.parse("22/07/2018"), 80000.0, cat1);
		Veiculo vei2 = new Veiculo(null, "Mustang", sdf.parse("17/05/1969 "), 120000.0, cat2);
		Veiculo vei3 = new Veiculo(null, "Cruze", sdf.parse("08/07/2015 "), 50000.0, cat3);
		Veiculo vei4 = new Veiculo(null, "C4 Lounge", sdf.parse("16/03/2010"), 40000.0, cat4);
		Veiculo vei5 = new Veiculo(null, "CR-V", sdf.parse("03/07/2007"), 37000.0, cat5);
		Veiculo vei6 = new Veiculo(null, "Corolla XEI", sdf.parse("29/05/2020"), 86000.0, cat1);

		veiRepository.saveAll(Arrays.asList(vei1,vei2,vei3,vei4,vei5,vei6));

		Cliente cli1 = new Cliente(null, "Crash", "crashnight089@gmail.com", "123");
		Cliente cli2 = new Cliente(null, "Alex", "alex@gmail.com", "123");
		cliRepository.saveAll(Arrays.asList(cli1,cli2));
	}

}
