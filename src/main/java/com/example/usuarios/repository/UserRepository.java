package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
