package com.gfarkas.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.gfarkas.restservices.entities.User;
import com.gfarkas.restservices.entities.Views;
import com.gfarkas.restservices.exceptions.UserNotFoundException;
import com.gfarkas.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/jsonview/users")
public class UserJsonViewController {
	
	UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	//getUserById - External
	@GetMapping("/external/{id}") 
	@JsonView(Views.External.class)
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {

			return userService.getUserById(id);

		} catch (UserNotFoundException ex) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

	}

	//getUserById - Internal
	@GetMapping("/internal/{id}") 
	@JsonView(Views.Internal.class)
	public Optional<User> getUserByIdInternal(@PathVariable("id") @Min(1) Long id) {

		try {

			return userService.getUserById(id);

		} catch (UserNotFoundException ex) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

	}

}
