package com.example.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usuarios.dto.UserCreatedDTO;
import com.example.usuarios.dto.UserPatchDTO;
import com.example.usuarios.dto.UserUpdateDTO;
import com.example.usuarios.entity.User;
import com.example.usuarios.excepitons.ResourceNotFoundException;
import com.example.usuarios.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<User> findAll() {
		
		return repository.findAll();
	}
	
	public User findById(Long id) {
		
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com id " + id));
		
	}
	
	public User created(User user) {
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		return repository.save(user);
	}
	
	public User update(Long id, UserUpdateDTO dto) {
		
		if(dto.name() == null || dto.email() == null || dto.senha() == null) {
			throw new RuntimeException("Todos os campos são obrigatorio.");
		}
			
	
		User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com o id " + id));
		
	
		updateUser(entity, dto);

		return repository.save(entity);
	
	}
	
	private void updateUser(User entity, UserUpdateDTO user) {
		entity.setEmail(user.email());
		entity.setName(user.name());
		entity.setSenha(passwordEncoder.encode(user.senha()));
		
	}

	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User fromDTO(UserCreatedDTO dto) {
		User user = new User();
		user.setEmail(dto.email());
		user.setName(dto.name());
		user.setSenha(dto.senha());
		
		return user;
	}
	
	public User patchUser(Long id, UserPatchDTO dto) {
		
		User entity = repository.findById(id).get();
		
		patchUpdateUser(entity, dto);
		
		return repository.save(entity);
	}
	
	private void patchUpdateUser(User entity, UserPatchDTO dto) {
		
		if(dto.email() != null) {
			entity.setEmail(dto.email());
		}
		
		if(dto.name() != null) {
			entity.setName(dto.name());
		}
		
		if(dto.senha() != null) {
			entity.setSenha(passwordEncoder.encode(dto.senha()));
		}
		
		
	}

	
	
}
