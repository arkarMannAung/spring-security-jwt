package com.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.dto.CredentialDto;
import com.spring.jwt.dto.SignUpDto;
import com.spring.jwt.services.AuthService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody CredentialDto credentialDto) {
		return authService.login(credentialDto);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody SignUpDto signUpDto) {
		return authService.register(signUpDto);
	}
	
	// TODO: Testing purpose
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser() {
		return ResponseEntity.ok("User Is Createing");
	}
	// TODO: Testing purpose
	@GetMapping("/public")
	public ResponseEntity<String> index() {
		return ResponseEntity.ok("This is public");
	}
}
