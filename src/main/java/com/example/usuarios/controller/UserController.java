package com.example.usuarios.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.usuarios.dto.UserCreatedDTO;
import com.example.usuarios.dto.UserPatchDTO;
import com.example.usuarios.dto.UserResponseDTO;
import com.example.usuarios.dto.UserUpdateDTO;
import com.example.usuarios.entity.User;
import com.example.usuarios.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll(){
		List<User> users = service.findAll();
		
		List<UserResponseDTO> list = users.stream()
				.map(user ->  new UserResponseDTO(
						user.getId(),
						user.getName(),
						user.getEmail())).toList();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
		User user = service.findById(id);
		
		UserResponseDTO obj = new UserResponseDTO(
				user.getId(),
				user.getName(),
				user.getEmail()
				
				);
		
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> postUser(@RequestBody UserCreatedDTO dto){
		
		User user = service.fromDTO(dto);
		user = service.created(user);
		
		UserResponseDTO response = new UserResponseDTO(user);
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id,@RequestBody UserUpdateDTO dto){
	
		User obj = service.update(id, dto);
		
		UserResponseDTO response = new UserResponseDTO(
				obj.getId(),
				obj.getName(),
				obj.getEmail()
				
				);
		
		return ResponseEntity.ok().body(response);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> patch(@PathVariable Long id, @RequestBody UserPatchDTO dto){
		User obj = service.patchUser(id, dto);
		
		UserResponseDTO response = new UserResponseDTO(
				obj.getId(),
				obj.getName(),
				obj.getEmail()
				
				);
		
		return ResponseEntity.ok().body(response);
		
	}
	
}
