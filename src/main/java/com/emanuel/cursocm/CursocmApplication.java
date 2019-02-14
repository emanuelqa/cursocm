package com.emanuel.cursocm;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emanuel.cursocm.domain.Categoria;
import com.emanuel.cursocm.domain.Produto;
import com.emanuel.cursocm.repositories.CategoriaRepository;
import com.emanuel.cursocm.repositories.ProdutoRepository;

@SpringBootApplication
public class CursocmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursocmApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Teclado", 48.50);
		Produto p2 = new Produto(null, "Cadeira", 150.00);
		Produto p3 = new Produto(null, "Mouse", 20.79);
		
		cat1.setProdutos(Arrays.asList(p1, p3));
		cat2.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2 ,p3));
		
	}

}

