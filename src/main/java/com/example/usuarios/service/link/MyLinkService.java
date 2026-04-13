package com.example.usuarios.service.link;

import com.example.usuarios.entity.Link;
import com.example.usuarios.entity.User;
import com.example.usuarios.excepitons.ResourceNotFoundException;
import com.example.usuarios.repository.LinkRepository;
import com.example.usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyLinkService {

    @Autowired
    private LinkRepository repository;


    @Autowired
    private UserRepository userRepository;


    public List<Link> findMyLinks(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o email" + email));

        return repository.findByUser(user);
    }

    public Link createMyLink(Link link, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o email" + email));

        Link newLink = new Link();
        newLink.setName(link.getName());
        newLink.setUrl(link.getUrl());
        newLink.setUser(user);

        return repository.save(newLink);

    }

    public Link updateMyLink(Link link, Long id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o email" + email));

        Link entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Link nao encotrado com o di"+ id));


        updateMylink(entity, link, user);

        return repository.save(entity);
    }


    public void deleteMyLinks(Long id, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encotrado com o email" + email));

        Link link = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Link nao encotrado com o id" + id));

        if(!link.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Você não tem permissão para excluir este link");
        }

        repository.delete(link);
    }


    private void updateMylink(Link entity, Link link, User user) {

        if(	link.getName() != null) {
            entity.setName(link.getName());
            entity.setUser(user);
        }

        if(link.getUrl() != null) {
            entity.setUrl(link.getUrl());
            entity.setUser(user);
        }

    }

}
