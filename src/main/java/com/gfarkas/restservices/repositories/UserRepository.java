package com.gfarkas.restservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gfarkas.restservices.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();
	
	User findByUsername(String username);

}
