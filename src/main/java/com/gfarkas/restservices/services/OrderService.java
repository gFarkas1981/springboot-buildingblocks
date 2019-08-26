package com.gfarkas.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfarkas.restservices.entities.Order;
import com.gfarkas.restservices.entities.User;
import com.gfarkas.restservices.exceptions.OrderNotFoundException;
import com.gfarkas.restservices.exceptions.UserNotFoundException;
import com.gfarkas.restservices.repositories.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepository;

	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> getAllOrders() {

		return orderRepository.findAll();

	}

	public Optional<Order> getOrderById(Long id) throws OrderNotFoundException {

		Optional<Order> order = orderRepository.findById(id);

		if (!order.isPresent())
			throw new OrderNotFoundException("Order not found in @OrderRepository, please provide a correct order ID");

		return order;

	}
	

}
