package com.example.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.dto.TokenResponse;
import com.example.usuarios.dto.UserLoginDTO;
import com.example.usuarios.entity.User;
import com.example.usuarios.service.TokenService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth")
public class LoginController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<TokenResponse> login(@RequestBody UserLoginDTO data){
		
		System.out.println("ENTROU NO LOGIN");
	
		
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(data.email(), data.senha());
		
		Authentication authentication = authenticationManager.authenticate(authToken);
		
		User user = (User) authentication.getPrincipal();
		
		String token = tokenService.generateToken(user);
		System.out.println(token);
		
		
		 return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
	}
}
