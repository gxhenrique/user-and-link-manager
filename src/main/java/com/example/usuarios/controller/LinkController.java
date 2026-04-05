package com.example.usuarios.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.dtolink.LinkCreatedDTO;
import com.example.usuarios.entity.Link;
import com.example.usuarios.service.LinkService;

@RestController
@RequestMapping(value = "/links")
public class LinkController {
	
	
	@Autowired
	private LinkService service;
	
	@GetMapping
	public ResponseEntity<List<Link>> findALL(){
		
		List<Link> obj = service.findALL();
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Link> findById(@PathVariable Long id){
		Link obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Link> created(@RequestBody  LinkCreatedDTO dto){
		
		Link link = service.created(dto);
		
		return ResponseEntity.ok().body(link);
	}
	
	
	

}
