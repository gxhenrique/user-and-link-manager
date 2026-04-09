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
import com.example.usuarios.dto.UserResponseLinksDTO;
import com.example.usuarios.dto.UserUpdateDTO;
import com.example.usuarios.entity.User;
import com.example.usuarios.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;
	
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> postUser(@RequestBody UserCreatedDTO dto){
		
		User user = service.fromDTO(dto);
		user = service.created(user);
		
		UserResponseDTO response = new UserResponseDTO(user);
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	
	
	
}
