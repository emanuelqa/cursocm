package com.emanuel.cursocm;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emanuel.cursocm.domain.Categoria;
import com.emanuel.cursocm.repositories.CategoriaRepository;

@SpringBootApplication
public class CursocmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursocmApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}

}

