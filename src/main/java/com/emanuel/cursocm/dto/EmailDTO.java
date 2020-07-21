package com.emanuel.cursocm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO {
	
	@NotEmpty(message = "O email não pode ser vazio.")
	@Email(message = "Emails inválido")
	private String email;
	
	public EmailDTO() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
