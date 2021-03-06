package com.emanuel.cursocm.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.emanuel.cursocm.dto.ClienteNewDTO;
import com.emanuel.cursocm.enuns.TipoCliente;
import com.emanuel.cursocm.repositories.ClienteRepository;
import com.emanuel.cursocm.services.exceptions.FieldMenssage;
import com.emanuel.cursocm.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMenssage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMenssage("cpfOuCnpj", "CPF inválido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMenssage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		if(clienteRepository.findByEmail(objDto.getEmail()) != null) {
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
