package com.tcs.slackintegration.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.tcs.slackintegration.exception.ResourceNotFoundException;
import com.tcs.slackintegration.model.Users;
import com.tcs.slackintegration.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users/{id}")
	public ResponseEntity<Users> getUser(@PathVariable(value = "id") Integer userId)
			throws ResourceNotFoundException {
		Users users = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(users);
	}

	@PostMapping("/users")
	public Users createUser(@RequestBody Users users) {
		return userRepository.save(users);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable(value = "id") Integer userId,
			@RequestBody Users userDetails) throws ResourceNotFoundException {
		Users users = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		users.setEmail(userDetails.getEmail());
		users.setName(userDetails.getName());
		users.setCountry_code(userDetails.getCountry_code());
		users.setContact_no(userDetails.getContact_no());	
		
		final Users updatedUser = userRepository.save(users);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer userId)
			throws ResourceNotFoundException {
		Users users = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));

		userRepository.delete(users);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}