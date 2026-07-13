package com.example.financetracker.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.financetracker.model.Users;
import com.example.financetracker.repository.UserRepository;
import com.example.security.dto.LoginRequest;
import com.example.security.jwt.JwtUtil;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:5173")
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

	    Optional<Users> optionalUser =
	            userRepository.findByEmail(request.getEmail());


	    if(optionalUser.isPresent()) {

	        Users user = optionalUser.get();


	        // Compare entered password with BCrypt password
	        if(passwordEncoder.matches(
	                request.getPassword(),
	                user.getPassword())) {


	            String token = JwtUtil.generateToken(user.getEmail());


	            System.out.println("Finance Tracker app logged in");

	            System.out.println(
	                "Username: " + user.getEmail()
	            );


	            Map<String,Object> response = new HashMap<>();

	            response.put("token", token);
	            response.put("email", user.getEmail());
	            response.put("userId", user.getUserid());


	            return ResponseEntity.ok(response);
	        }
	    }


	    return ResponseEntity
	            .status(HttpStatus.UNAUTHORIZED)
	            .body("Invalid Credentials");
	}

}
