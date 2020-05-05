package com.emanuel.cursocm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.dto.ClienteDTO;
import com.emanuel.cursocm.repositories.ClienteRepository;
import com.emanuel.cursocm.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElse(null);
	}
	
	public Cliente update(Cliente cliente) {
		Cliente clienteNew = find(cliente.getId());
		atualizar(clienteNew, cliente);
		return repo.save(clienteNew);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente.");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	private void atualizar(Cliente clienteNew, Cliente cliente) {
		clienteNew.setEmail(cliente.getEmail());
		clienteNew.setNome(cliente.getNome());
	}
}
