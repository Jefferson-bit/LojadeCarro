package com.crash.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailServiceImpl.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando Email");
		LOG.info(msg.toString());
		LOG.info("Email enviado com sucesso");
	}
}
