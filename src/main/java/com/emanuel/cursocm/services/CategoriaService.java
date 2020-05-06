package com.emanuel.cursocm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.emanuel.cursocm.domain.Categoria;
import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.dto.CategoriaDTO;
import com.emanuel.cursocm.repositories.CategoriaRepository;
import com.emanuel.cursocm.services.exceptions.DataIntegrityException;
import com.emanuel.cursocm.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id){
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria categoria) {
		return repo.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		Categoria categoriaNew = find(categoria.getId());
		atualizar(categoriaNew, categoria);
		return repo.save(categoriaNew);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void atualizar(Categoria categoriaNew, Categoria categoria) {
		categoriaNew.setNome(categoria.getNome());
	}

}
