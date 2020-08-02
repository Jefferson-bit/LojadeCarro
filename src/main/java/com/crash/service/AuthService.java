package com.crash.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crash.domain.Cliente;
import com.crash.repositories.ClienteRepository;
import com.crash.service.exceptions.ResourceNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository cliRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	
	private Random rand = new Random();
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {

		Cliente obj = cliRepository.findByEmail(email);
		if (obj == null) {
			throw new ResourceNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		obj.setSenha(pe.encode(newPass));
		cliRepository.save(obj);
		emailService.sendNewPasswordEmail(obj, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
