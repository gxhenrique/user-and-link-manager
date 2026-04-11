package com.example.usuarios.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.usuarios.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {
	
	private String chave = "minha-chave-super-secreta-com-32-bytes";
	
	private Key getKey() {
		return Keys.hmacShaKeyFor(chave.getBytes());
    }
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.setExpiration(new Date(System.currentTimeMillis()  + 1000 * 60 * 60))
				.signWith(getKey())
				.compact();
	}

	public String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(chave.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); // 🔥 retorna o email

        } catch (Exception e) {
            return null; // token inválido
        }
    }
	
	
}
