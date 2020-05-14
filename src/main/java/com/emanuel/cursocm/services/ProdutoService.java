package com.emanuel.cursocm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.emanuel.cursocm.domain.Categoria;
import com.emanuel.cursocm.domain.Produto;
import com.emanuel.cursocm.repositories.CategoriaRepository;
import com.emanuel.cursocm.repositories.ProdutoRepository;
import com.emanuel.cursocm.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id){
		Optional<Produto> produto = repo.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> share(String nome, String idsCategoria, Integer page, Integer linesPerPage, String orderBy, String direction){
		List<Integer> ids = Utils.converterStingForIdsInteger(idsCategoria);
		List<Categoria> categorias =  categoriaRepository.findAllById(ids);
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}
