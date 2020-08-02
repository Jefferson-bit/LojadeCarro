package com.crash.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crash.domain.dto.EmailDTO;
import com.crash.service.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private AuthService service;
	
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void>forgot(@Valid @RequestBody EmailDTO objDto){
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.ok().build();
	}
	
}
