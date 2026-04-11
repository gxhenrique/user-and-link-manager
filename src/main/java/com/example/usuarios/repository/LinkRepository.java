package com.example.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.entity.Link;
import com.example.usuarios.entity.User;

public interface LinkRepository extends JpaRepository<Link, Long> {
	List<Link> findByUser(User user);
}
