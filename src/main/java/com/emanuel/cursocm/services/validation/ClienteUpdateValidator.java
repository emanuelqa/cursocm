package com.emanuel.cursocm.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.dto.ClienteDTO;
import com.emanuel.cursocm.repositories.ClienteRepository;
import com.emanuel.cursocm.services.exceptions.FieldMenssage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

		List<FieldMenssage> list = new ArrayList<>();

		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.valueOf(map.get("id"));
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMenssage("email", "Email já existe"));
		}
		
		for (FieldMenssage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
