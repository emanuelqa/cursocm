package com.emanuel.cursocm.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.emanuel.cursocm.security.UserSpringSecurity;

public class UserService {
	
	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
