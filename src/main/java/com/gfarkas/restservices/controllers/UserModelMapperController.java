package com.gfarkas.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfarkas.restservices.dtos.UserMmDto;
import com.gfarkas.restservices.entities.User;
import com.gfarkas.restservices.exceptions.UserNotFoundException;
import com.gfarkas.restservices.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	
	private UserService userService;
	private ModelMapper modelMapper;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}



	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {


		Optional<User> userOptional = userService.getUserById(id);

			if (!userOptional.isPresent()) {
				
				throw new UserNotFoundException("User not found");
				
			}
			
			User user = userOptional.get();
			
			UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
			
			return userMmDto;

	}

}
