package com.gfarkas.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfarkas.restservices.entities.Order;
import com.gfarkas.restservices.entities.User;
import com.gfarkas.restservices.exceptions.UserNotFoundException;
import com.gfarkas.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class OrderHateoasController {

	private UserRepository userRepository;


	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found");

		List<Order> allOrders = userOptional.get().getOrders();
		Resources<Order> finalResources = new Resources<Order>(allOrders);

		return finalResources;
		
	}

}
