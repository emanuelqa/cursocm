package com.emanuel.cursocm.services.exceptions;

import java.io.Serializable;

public class FieldMenssage implements Serializable{
	private static final long serialVersionUID = 1L;

	private String field;
	private String mensagem;
	
	public FieldMenssage() {}

	public FieldMenssage(String field, String mensagem) {
		this.field = field;
		this.mensagem = mensagem;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
