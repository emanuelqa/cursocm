package com.emanuel.cursocm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.repositories.ClienteRepository;
import com.emanuel.cursocm.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(email == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(cliente.getId(), cliente.getSenha(), cliente.getEmail(), cliente.getPerfis());
	}

}
