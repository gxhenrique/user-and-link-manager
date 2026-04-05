package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
