package com.gfarkas.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfarkas.restservices.entities.User;
import com.gfarkas.restservices.exceptions.UserAlreadyExistsException;
import com.gfarkas.restservices.exceptions.UserNotFoundException;
import com.gfarkas.restservices.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {

		return userRepository.findAll();

	}

	public User createUser(User user) throws UserAlreadyExistsException {

		User existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser != null)
			throw new UserAlreadyExistsException(
					"This user is already exists in repository, please provide another one");

		return userRepository.save(user);

	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("User not found in User repository, please provide a correct user ID");

		return user;

	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {

		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User not found in User repository, please provide a correct user ID");

		user.setId(id);
		return userRepository.save(user);

	}

	public void deleteUserById(Long id) throws UserNotFoundException {

		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent())
			throw new UserNotFoundException("User not found in User repository, please provide a correct user ID");

		userRepository.deleteById(id);

	}

	public User getUserByUsername(String username) {

		return userRepository.findByUsername(username);

	}

}
