package com.example.usuarios.dto;


import java.time.LocalDate;

public record UserCreatedDTO(
        String name, String usernameCustom,
        LocalDate dataNascimento, String email, String senha) {
	
}
