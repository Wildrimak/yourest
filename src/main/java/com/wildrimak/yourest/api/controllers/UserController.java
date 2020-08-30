package com.wildrimak.yourest.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wildrimak.yourest.domain.models.User;
import com.wildrimak.yourest.domain.repositories.UserRepository;
import com.wildrimak.yourest.domain.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	private List<User> getUsers() {
		return userRepository.findAll();
	}

	@PostMapping
	private ResponseEntity<User> postUser(@RequestBody User user) {

		User userSaved = userService.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
	}

}
