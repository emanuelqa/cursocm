package com.emanuel.cursocm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emanuel.cursocm.domain.Cidade;
import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.domain.Endereco;
import com.emanuel.cursocm.dto.ClienteDTO;
import com.emanuel.cursocm.dto.ClienteNewDTO;
import com.emanuel.cursocm.enuns.TipoCliente;
import com.emanuel.cursocm.repositories.ClienteRepository;
import com.emanuel.cursocm.repositories.EnderecoRepository;
import com.emanuel.cursocm.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElse(null);
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente = repo.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
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
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(), 
										TipoCliente.toEnum(clienteNewDTO.getTipo()), bCrypt.encode(clienteNewDTO.getSenha()));
		Cidade cidade = new Cidade(clienteNewDTO.getCidade(), null, null);
		Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(), clienteNewDTO.getComplemento(),
				clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cidade);
		cliente.getEnderecos().add(endereco);
		endereco.setCliente(cliente);
		cliente.getTelefones().add(clienteNewDTO.getTelefone1());
		if(clienteNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone2());
		}
		if(clienteNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteNewDTO.getTelefone3());
		}
		return cliente;
	}
	
	
	private void atualizar(Cliente clienteNew, Cliente cliente) {
		clienteNew.setEmail(cliente.getEmail());
		clienteNew.setNome(cliente.getNome());
	}
}
