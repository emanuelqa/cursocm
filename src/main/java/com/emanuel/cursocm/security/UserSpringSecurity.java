package com.emanuel.cursocm.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.emanuel.cursocm.enuns.Perfil;

public class UserSpringSecurity implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String senha;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSpringSecurity() {}
	
	public UserSpringSecurity(Integer id, String senha, String email, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.senha = senha;
		this.email = email;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Integer getId() {
		return this.id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
