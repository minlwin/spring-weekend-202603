package com.jdc.users.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.users.model.UserRepo;

@RestController
@RequestMapping("users")
public class UsersApi {
	
	@Autowired
	private UserRepo repo;

	@GetMapping
	List<UserDto> findAll() {
		return repo.findAll()
				.stream()
				.map(UserDto::from)
				.toList();
	}
	
	// /users/1
	// /users/10
	@GetMapping("{id}")
	UserDto findById(@PathVariable int id) {
		return repo.findById(id)
				.map(UserDto::from)
				.orElseThrow();
	}
}
