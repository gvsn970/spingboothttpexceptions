package com.onpassive.springbootexceptionhttps.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RolesController {

	@Autowired
	RolesRepository userRepository;

	@Autowired
	RoleService userService;

	@PostMapping("/create")
	public ResponseEntity<Object> createRole(@RequestBody RolesModel user) {

		return userService.createUser(user);

	}

	@GetMapping("/details/{id}")
	public RolesModel getUser(@PathVariable Long id) {

		if (userRepository.findById(id).isPresent())

			return userRepository.findById(id).get();

		else
			return null;

	}

	@GetMapping("/all")
	public List<RolesModel> getUsers() {

		return userRepository.findAll();

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody RolesModel user) {

		return userService.updateUser(user, id);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

		return userService.deleteUser(id);

	}
}
