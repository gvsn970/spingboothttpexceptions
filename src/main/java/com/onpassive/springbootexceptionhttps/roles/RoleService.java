package com.onpassive.springbootexceptionhttps.roles;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	RolesRepository userRepository;

	public ResponseEntity<Object> createUser(RolesModel model) {
		RolesModel user = new RolesModel();
		RolesModel nameObj = userRepository.findByRoleName(model.getRoleName());
		if (nameObj != null) {
			if (nameObj.getRoleName().equals(model.getRoleName())) {
				return ResponseEntity.badRequest()
						.body("The Role is already Present, Failed to Create new Role");
			}

		} else {
			user.setRoleName(model.getRoleName());
			user.setCreatedAt(model.getCreatedAt());
			RolesModel savedUser = userRepository.save(user);
			if (userRepository.findById(savedUser.getId()).isPresent())
				return ResponseEntity.ok("Role Created Successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed Creating Role as Specified");
		}
		return null;
	}

	/** Update an Existing User */
	@Transactional
	public ResponseEntity<Object> updateUser(RolesModel user, Long id) {
		if (userRepository.findById(id).isPresent()) {
			RolesModel newUser = userRepository.findById(id).get();
			newUser.setRoleName(user.getRoleName());
			newUser.setCreatedAt(user.getCreatedAt());
			RolesModel savedUser = userRepository.save(newUser);
			if (userRepository.findById(savedUser.getId()).isPresent())
				return ResponseEntity.accepted().body("Role updated successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed updating the Role specified");
		} else
			return ResponseEntity.unprocessableEntity().body("Cannot find the Role specified");
	}

	/** Delete an User */
	public ResponseEntity<Object> deleteUser(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			if (userRepository.findById(id).isPresent())
				return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified Permission");
			else
				return ResponseEntity.ok().body("Successfully deleted the specified Role");
		} else
			return ResponseEntity.badRequest().body("Cannot find the Role specified");
	}
}
