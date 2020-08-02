package com.crash.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.crash.domain.Cliente;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender")
	private String sender;

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage ms = preparandoSimpleMailMessage(cliente, newPass);
		sendEmail(ms);
	}

	protected SimpleMailMessage preparandoSimpleMailMessage(Cliente cliente, String newPass) {
		SimpleMailMessage ms = new SimpleMailMessage();
		ms.setTo(cliente.getEmail());// pegando email do cliente
		ms.setFrom(sender);// pegando default.sender que está no application.properties
		ms.setSubject("Solicitação de uma nova senha");// Messagem opcional
		ms.setSentDate(new Date(System.currentTimeMillis()));// Pegando horariod o sistema
		ms.setText("Nova senha: " + newPass);// mostrando a senha
		return ms;
	}
}
