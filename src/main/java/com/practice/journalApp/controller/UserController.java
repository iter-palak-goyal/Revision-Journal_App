package com.practice.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.practice.journalApp.entity.User;
import com.practice.journalApp.repository.UserRepository;
import com.practice.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User>getAllUsers() {
		return userService.getAll();
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		userService.saveEntry(user);
	}
	
	@PutMapping("/{userName}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
		User userInDB  = userService.findByUserName(userName);
		if(userInDB != null ) {
			userInDB.setUserName(user.getUserName());
			userInDB.setPassword(user.getPassword());
			userService.saveEntry(userInDB);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
