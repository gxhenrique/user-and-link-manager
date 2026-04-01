package com.example.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usuarios.dto.UserCreatedDTO;
import com.example.usuarios.dto.UserUpdateDTO;
import com.example.usuarios.entity.User;
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
		return obj.orElseThrow(() -> new RuntimeException("Usuario não encontrado com id" + id));
		
	}
	
	public User created(User user) {
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		return repository.save(user);
	}
	
	public User update(Long id, UserUpdateDTO dto) {
		
		User entity = repository.findById(id).get();
	
		updateUser(entity, dto);
		
		return repository.save(entity);

	}
	
	private void updateUser(User entity, UserUpdateDTO user) {
		entity.setEmail(user.email());
		entity.setName(user.name());
		entity.setSenha(user.senha());
		
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
	
	
}
