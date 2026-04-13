package com.example.usuarios.controller.links;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.dtolink.LinkCreatedDTO;
import com.example.usuarios.dtolink.LinkResponseDTO;
import com.example.usuarios.dtolink.LinkUpdateDTO;
import com.example.usuarios.entity.Link;
import com.example.usuarios.service.link.LinkService;

@RestController
@RequestMapping(value = "/admin/links")
public class LinkController {


    @Autowired
    private LinkService service;

    @GetMapping
    public ResponseEntity<List<LinkResponseDTO>> findALL() {

        List<Link> links = service.findALL();

        List<LinkResponseDTO> list = links.stream().map(link -> new LinkResponseDTO(
                link.getId(),
                link.getName(),
                link.getUrl())).toList();

        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<LinkResponseDTO> findById(@PathVariable Long id) {

        Link obj = service.findById(id);
        LinkResponseDTO link = new LinkResponseDTO(
                obj.getId(),
                obj.getName(),
                obj.getUrl());

        return ResponseEntity.ok().body(link);
    }

    @PostMapping
    public ResponseEntity<Link> created(@RequestBody LinkCreatedDTO dto) {

        Link link = service.created(dto);

        return ResponseEntity.ok().body(link);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Link> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Link> update(@PathVariable Long id, @RequestBody LinkUpdateDTO dto) {

        Link response = service.update(id, dto);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Link> patch(@PathVariable Long id, @RequestBody LinkUpdateDTO dto) {

        Link response = service.patch(id, dto);

        return ResponseEntity.ok().body(response);
    }


}
