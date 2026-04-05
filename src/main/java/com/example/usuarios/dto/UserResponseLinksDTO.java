package com.example.usuarios.dto;

import java.util.List;

import com.example.usuarios.entity.Link;
import com.example.usuarios.entity.User;

public record UserResponseLinksDTO(Long id, String name, String email, List<Link> link) {
	public UserResponseLinksDTO(User user) {
		this(user.getId(),user.getName(), user.getEmail(), user.getLink());
	}
}
