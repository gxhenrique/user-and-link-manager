package com.example.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usuarios.dtolink.LinkCreatedDTO;
import com.example.usuarios.dtolink.LinkUpdateDTO;
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
	
	public Link update(Long id, LinkUpdateDTO dto) {
		
		Link entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o id" + id));
		
		updateLink(entity, dto);
		
		return repository.save(entity);
	}
	

	
	private void updateLink (Link entity, LinkUpdateDTO dto) {
		entity.setName(dto.name());
		entity.setUrl(dto.url());
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Link patch(Long id, LinkUpdateDTO dto) {
		
		Link entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o id" + id));
		
		patchLink(entity, dto);
		
		return repository.save(entity);
	}
	
	private void patchLink(Link entity, LinkUpdateDTO dto) {
		
		if(	dto.name() != null) {
			entity.setName(dto.name());
		}
		
		if(dto.url() != null) {
			entity.setUrl(dto.url());
		}
		
	}
	
	// service para user logado ---  ajusta depois
	
	public List<Link> findMyLinks(String email){
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o email" + email));
		
		return repository.findByUser(user);
	}
	
	
	public void deleteMyLinks(Long id, String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o email" + email));
		
		Link link = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Link nao encotrado com o id" + id));
		
		if(!link.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Você não tem permissão para excluir este link");
		}
		
		repository.delete(link);
	}
	
	
	
	
	
	
	
	
}
