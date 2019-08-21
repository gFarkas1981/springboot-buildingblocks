package com.gfarkas.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.gfarkas.restservices.entities.User;
import com.gfarkas.restservices.exceptions.UserAlreadyExistsException;
import com.gfarkas.restservices.exceptions.UserNotFoundException;
import com.gfarkas.restservices.services.UserService;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> getAllUser() {

		return userService.getAllUsers();

	}

	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {

		try {

			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		} catch (UserAlreadyExistsException ex) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());

		}

	}

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {

		try {

			return userService.getUserById(id);

		} catch (UserNotFoundException ex) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

	}

	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {

		try {

			return userService.updateUserById(id, user);

		} catch (UserNotFoundException ex) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());

		}

	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {

		try {

			userService.deleteUserById(id);

		} catch (UserNotFoundException ex) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());

		}

	}

	@GetMapping("/users/findbyusername/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {

		return userService.getUserByUsername(username);

	}

}
