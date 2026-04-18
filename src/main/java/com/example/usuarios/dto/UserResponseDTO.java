package com.example.usuarios.dto;

import com.example.usuarios.entity.User;

import java.time.LocalDate;

public record UserResponseDTO(Long id, String name, String email, LocalDate dataNascimento, String userNameCustom) {
	public UserResponseDTO(User user) {
		this(user.getId(),user.getName(), user.getEmail(), user.getDataNascimento(),user.getUsernameCustom());
	}
}
