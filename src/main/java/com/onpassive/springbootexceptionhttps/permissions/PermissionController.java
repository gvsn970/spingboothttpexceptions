package com.onpassive.springbootexceptionhttps.permissions;

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
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	PermissionRepository userRepository;

	@Autowired
	PermissionService userService;

	@PostMapping("/create")
	public ResponseEntity<Object> createUser(@RequestBody PermissionModel user) {

		return userService.createUser(user);

	}

	@GetMapping("/details/{id}")
	public PermissionModel getUser(@PathVariable Long id) {

		if (userRepository.findById(id).isPresent())

			return userRepository.findById(id).get();

		else
			return null;

	}

	@GetMapping("/all")
	public List<PermissionModel> getUsers() {

		return userRepository.findAll();

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody PermissionModel user) {

		return userService.updateUser(user, id);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

		return userService.deleteUser(id);

	}
}
