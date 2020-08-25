package com.crash.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crash.domain.Categoria;
import com.crash.domain.Cliente;
import com.crash.domain.Detalhes;
import com.crash.domain.Veiculo;
import com.crash.domain.enums.Perfil;
import com.crash.repositories.CategoriaRepository;
import com.crash.repositories.ClienteRepository;
import com.crash.repositories.DetalhesRepository;
import com.crash.repositories.VeiculoRepository;

@Service
public class DBService{

		@Autowired
		private CategoriaRepository catRepository;
		
		@Autowired
		private ClienteRepository cliRepository;
		
		@Autowired
		private VeiculoRepository veiRepository;
		
		@Autowired
		private DetalhesRepository detRepository;
		
		@Autowired BCryptPasswordEncoder pe;
		
		private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		public void instanteDataBase() throws Exception {
			
		Categoria cat1 = new Categoria(null, "Toyota");
		Categoria cat2 = new Categoria(null, "Ford");
		Categoria cat3 = new Categoria(null, "Chevrolet");
		Categoria cat4 = new Categoria(null, "Citroen");
		Categoria cat5 = new Categoria(null, "Honda");

		catRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5));
			
		Detalhes det1 = new Detalhes(null, "Azul", 40000.0, 4, "Manual", "Corolla GLI perfeitamente em um otimo estados");
		Detalhes det2 = new Detalhes(null, "Preto", 10000.0, 4, "Manual", "Mustang Na minha opinião, o melhor carro que existe");
		Detalhes det3 = new Detalhes(null, "Branco", 32000.0, 4, "Automatico", "Cruze um bom carro para fazer compra, apenas");
		Detalhes det4 = new Detalhes(null, "Vermelho", 16000.0, 2, "Automatico", "C4 Lounge Carro bacana pra fazer compra de pão nada mais");
		Detalhes det5 = new Detalhes(null, "Azul", 29000.0, 2, "Manual", "CR-V Nem eu sei que carro é esse, mas tá ai");
		Detalhes det6 = new Detalhes(null, "Branco", 0.0, 4, "Automatico", "Corolla XEI carro de 2020, custando dois rim");

		detRepository.saveAll(Arrays.asList(det1,det2,det3,det4,det5,det6));
			
		Veiculo vei1 = new Veiculo(null, "Corolla GLI", sdf.parse("22/07/2018"), 80000.0, "Automovel", cat1, det1);
		Veiculo vei2 = new Veiculo(null, "Mustang", sdf.parse("17/05/1969"), 120000.0, "Automovel", cat2, det2);
		Veiculo vei3 = new Veiculo(null, "Cruze", sdf.parse("17/05/2007"), 50000.0, "Automovel", cat3,det3);
		Veiculo vei4 = new Veiculo(null, "C4 Lounge", sdf.parse("16/03/2010"),  40000.0, "Automovel", cat4,det4);
		Veiculo vei5 = new Veiculo(null, "CR-V", sdf.parse("03/07/2007"), 37000.0, "Automovel", cat5,det5);
		Veiculo vei6 = new Veiculo(null, "Corolla XEI", sdf.parse("29/05/2020"), 86000.0, "Automovel", cat1,det6);
			
		veiRepository.saveAll(Arrays.asList(vei1,vei2,vei3,vei4,vei5,vei6));
		
		
		Cliente cli1 = new Cliente(null, "Crash", "crashnight089@gmail.com", pe.encode("123"));
		Cliente cli2 = new Cliente(null, "Alex", "alex@gmail.com", pe.encode("123"));
		cli2.addPerfil(Perfil.ADMIN);
		cliRepository.saveAll(Arrays.asList(cli1,cli2));
	}

}
