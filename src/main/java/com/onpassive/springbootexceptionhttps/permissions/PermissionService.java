package com.onpassive.springbootexceptionhttps.permissions;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

	@Autowired
	PermissionRepository userRepository;

	public ResponseEntity<Object> createUser(PermissionModel model) {
		 PermissionModel user = new PermissionModel();
		 PermissionModel nameObj= userRepository.findByPermissionName(model.getPermissionName());
	      if(nameObj !=null) {
	    	  if (nameObj.getPermissionName().equals(model.getPermissionName())) {
		            return ResponseEntity.badRequest().body("The Permisssion is already Present, Failed to Create new Permission");
	    	  }
				
	        } else {
	            user.setPermissionName(model.getPermissionName());
	            user.setCreatedAt(model.getCreatedAt());
	            PermissionModel savedUser = userRepository.save(user);
	            if (userRepository.findById(savedUser.getId()).isPresent())
	                return ResponseEntity.ok("Permission Created Successfully");
	            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
	        }
	      return null;
	    }

	/** Update an Existing User */
	@Transactional
	public ResponseEntity<Object> updateUser(PermissionModel user, Long id) {
		if (userRepository.findById(id).isPresent()) {
			PermissionModel newUser = userRepository.findById(id).get();
			newUser.setPermissionName(user.getPermissionName());
			newUser.setCreatedAt(user.getCreatedAt());
			PermissionModel savedUser = userRepository.save(newUser);
			if (userRepository.findById(savedUser.getId()).isPresent())
				return ResponseEntity.accepted().body("User updated successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
		} else
			return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
	}

	/** Delete an User */
	public ResponseEntity<Object> deleteUser(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			if (userRepository.findById(id).isPresent())
				return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified Permission");
			else
				return ResponseEntity.ok().body("Successfully deleted the specified user");
		} else
			return ResponseEntity.badRequest().body("Cannot find the user specified");
	}
}
