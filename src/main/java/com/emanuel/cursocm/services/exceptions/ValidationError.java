package com.emanuel.cursocm.services.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMenssage> error = new ArrayList();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMenssage> getError() {
		return error;
	}

	public void addError(String field, String msg) {
		this.error.add(new FieldMenssage(field, msg));
	}

}
