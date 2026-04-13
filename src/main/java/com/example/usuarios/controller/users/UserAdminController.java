package com.example.usuarios.controller.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.dto.UserPatchDTO;
import com.example.usuarios.dto.UserResponseDTO;
import com.example.usuarios.dto.UserResponseLinksDTO;
import com.example.usuarios.dto.UserUpdateDTO;
import com.example.usuarios.entity.User;
import com.example.usuarios.service.user.UserService;

@RestController
@RequestMapping(value = "/admin/users")
public class UserAdminController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserResponseLinksDTO>> findAll() {

		List<User> users = service.findAll();

		List<UserResponseLinksDTO> list = users.stream()
				.map(user -> new UserResponseLinksDTO(user.getId(), user.getName(), user.getEmail(), user.getLink()))
				.toList();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseLinksDTO> findById(@PathVariable Long id) {
		User user = service.findById(id);

		UserResponseLinksDTO obj = new UserResponseLinksDTO(user.getId(), user.getName(), user.getEmail(),
				user.getLink());

		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {

		User obj = service.update(id, dto);

		UserResponseDTO response = new UserResponseDTO(obj.getId(), obj.getName(), obj.getEmail());

		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> patch(@PathVariable Long id, @RequestBody UserPatchDTO dto) {

		User obj = service.patchUser(id, dto);

		UserResponseDTO response = new UserResponseDTO(obj.getId(), obj.getName(), obj.getEmail());

		return ResponseEntity.ok().body(response);

	}
}
