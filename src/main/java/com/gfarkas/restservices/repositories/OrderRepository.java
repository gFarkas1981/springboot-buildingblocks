package com.gfarkas.restservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gfarkas.restservices.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAll();

}
