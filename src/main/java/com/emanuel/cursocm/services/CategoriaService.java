package com.emanuel.cursocm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.cursocm.domain.Categoria;
import com.emanuel.cursocm.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElse(null);
	}

}
