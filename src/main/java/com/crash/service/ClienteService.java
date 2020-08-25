package com.crash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crash.domain.Cliente;
import com.crash.domain.dto.ClienteDTO;
import com.crash.domain.dto.ClienteNewDTO;
import com.crash.domain.enums.Perfil;
import com.crash.repositories.ClienteRepository;
import com.crash.security.UserSS;
import com.crash.service.exceptions.AuthorizationException;
import com.crash.service.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente findById(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> cli = repository.findById(id);
		return cli.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente cli = repository.findByEmail(email);
		if(cli == null) {
			throw new ResourceNotFoundException("Email Não encontrado: " + email);
		}
		return cli;
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Cliente update(Cliente obj, Integer id) {
		Cliente entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	public void updateData(Cliente objNew, Cliente obj) {
		objNew.setNome(obj.getNome());
		objNew.setEmail(obj.getEmail());
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pgRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pgRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null);
	}
	
	public Cliente fromNewDTO(ClienteNewDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
	}
}
