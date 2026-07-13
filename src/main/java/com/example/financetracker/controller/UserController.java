package com.example.financetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financetracker.model.Accounts;
import com.example.financetracker.model.Users;
import com.example.financetracker.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody Users user) {
		// Implement user registration logic here
		userService.registerUser(user);
		return "User registered successfully";
	}

	@PostMapping("/{userId}/addaccount")
	public Accounts addAccount(@PathVariable Long userId, @RequestBody Accounts account) {
		// Implement add account logic here
		return userService.addAccount(userId, account);
	}
	
	@GetMapping("/{userId}/getaccounts")
	public List<Accounts> getUserAccounts(@PathVariable Long userId) {
	    // Implement logic to retrieve user accounts here
	    return userService.getUserAccounts(userId);
	}
}
