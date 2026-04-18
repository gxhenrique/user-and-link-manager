package com.example.usuarios.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserPatchDTO(@NotBlank String name, String email, String senha,
                           String usernameCustom, String bio, String foto, LocalDate dataNascimento) {

}
