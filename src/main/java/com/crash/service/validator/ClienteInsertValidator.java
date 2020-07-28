package com.crash.service.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.crash.domain.Cliente;
import com.crash.domain.dto.ClienteNewDTO;
import com.crash.repositories.ClienteRepository;
import com.crash.resources.exceptions.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente cli = repository.findByEmail(objDto.getEmail());
		if( cli != null) {
			list.add(new FieldMessage("Email", "Email já existente"));
		}
		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {// esse for transportar os erros para o framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}