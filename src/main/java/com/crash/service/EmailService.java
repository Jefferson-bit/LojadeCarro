package com.crash.service;

import org.springframework.mail.SimpleMailMessage;

import com.crash.domain.Cliente;

public interface EmailService {
	
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Cliente cliente, String newPassword);
}
