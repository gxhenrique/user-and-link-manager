package com.example.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usuarios.dtolink.LinkCreatedDTO;
import com.example.usuarios.entity.Link;
import com.example.usuarios.entity.User;
import com.example.usuarios.excepitons.ResourceNotFoundException;
import com.example.usuarios.repository.LinkRepository;
import com.example.usuarios.repository.UserRepository;

@Service
public class LinkService {

  
	
	@Autowired
	private LinkRepository repository;
	
	
	@Autowired
	private UserRepository userRepository;


	public List<Link> findALL(){
		return repository.findAll();
	}
	
	public Link findById(Long id) {
		Optional<Link> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Link nao encotrado com o id " + id));
	}
	
	public Link created(LinkCreatedDTO dto) {
		
		User user = userRepository.findById(dto.userId())
				.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o id" + dto.userId()));
		
		Link link = new Link();
		link.setName(dto.name());
		link.setUrl(dto.url());
		link.setUser(user);
		
		return repository.save(link);
	}
	
	
	
	
	
	
	
}
