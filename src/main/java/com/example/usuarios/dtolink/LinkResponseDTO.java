package com.example.usuarios.dtolink;

import com.example.usuarios.entity.Link;

public record LinkResponseDTO(Long id, String name, String url) {
	public LinkResponseDTO(Link link) {
		this(link.getId(), link.getName(), link.getUrl());
	}
}
