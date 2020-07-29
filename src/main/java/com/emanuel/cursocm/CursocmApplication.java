package com.emanuel.cursocm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emanuel.cursocm.services.S3Service;

@SpringBootApplication
public class CursocmApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CursocmApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\Users\\Emanuel\\Downloads\\logo.png");
	}

}

