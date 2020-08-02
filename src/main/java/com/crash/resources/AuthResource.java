package com.crash.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crash.domain.dto.EmailDTO;
import com.crash.security.JWTUtil;
import com.crash.security.UserSS;
import com.crash.service.AuthService;
import com.crash.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private AuthService service;
	
	@Autowired 
	private JWTUtil jwtUtil;
	
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void>forgot(@Valid @RequestBody EmailDTO objDto){
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
}
