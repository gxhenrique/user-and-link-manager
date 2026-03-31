package com.example.usuarios.dto;

import com.example.usuarios.entity.User;

public record UserResponseDTO(Long id, String name, String email) {
	public UserResponseDTO(User user) {
		this(user.getId(),user.getName(), user.getEmail());
	}
}
