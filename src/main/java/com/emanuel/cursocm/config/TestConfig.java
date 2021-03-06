package com.emanuel.cursocm.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.emanuel.cursocm.services.DBService;
import com.emanuel.cursocm.services.EmailService;
import com.emanuel.cursocm.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateTestDataBase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;	
	}
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
