package com.gfarkas.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfarkas.restservices.entities.Order;
import com.gfarkas.restservices.entities.User;
import com.gfarkas.restservices.exceptions.OrderNotFoundException;
import com.gfarkas.restservices.exceptions.UserNotFoundException;
import com.gfarkas.restservices.repositories.OrderRepository;
import com.gfarkas.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {

	private UserRepository userRepository;
	private OrderRepository orderRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;		
	}

	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found");

		return userOptional.get().getOrders();

	}
	
	@GetMapping("/{userid}/orders/{orderid}")
	public Optional<Order> getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException, OrderNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found");

		Optional<Order> orderOptional = orderRepository.findById(orderid);

		if (!orderOptional.isPresent())
			throw new OrderNotFoundException("Order not found");

		
		return orderOptional;

	}
	
	
	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);

		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found");

		User user = userOptional.get();
		
		order.setUser(user);
		
		return orderRepository.save(order);
		
	}

}
