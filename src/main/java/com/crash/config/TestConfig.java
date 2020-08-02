package com.crash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.crash.service.DBService;
import com.crash.service.EmailService;
import com.crash.service.MockEmailService;
import com.crash.service.SMTPEmailServiceImpl;

@Configuration
@Profile("test")

public class TestConfig {

	@Autowired
	private DBService db;
	
	@Bean
	public boolean instanteData() throws Exception {
		db.instanteDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SMTPEmailServiceImpl();
	}
}
