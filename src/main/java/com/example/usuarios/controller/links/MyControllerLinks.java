package com.example.usuarios.controller.links;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.entity.Link;
import com.example.usuarios.service.LinkService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "user/me/links")
public class MyControllerLinks {
	
	@Autowired
	private LinkService service;
	
	@GetMapping
	public ResponseEntity<List<Link>> findAllLinks(Authentication authentication){
		
		String email = authentication.getName();
		System.out.println("EMAIL do authentication: " + email);
		List<Link> links = service.findMyLinks(email);
		
		return ResponseEntity.ok(links);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Link> deleteMyLink(@PathVariable Long id, Authentication authentication){
		String email = authentication.getName();
		service.deleteMyLinks(id, email);
		
		return ResponseEntity.noContent().build();
	}
}
