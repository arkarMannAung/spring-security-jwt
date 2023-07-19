package com.spring.jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.jwt.config.UserAuthProvider;
import com.spring.jwt.dto.CredentialDto;
import com.spring.jwt.dto.SignUpDto;
import com.spring.jwt.dto.UserDto;
import com.spring.jwt.entities.UserEntity;
import com.spring.jwt.exceptions.ApiExceptions;
import com.spring.jwt.repository.AuthMapper;

@Service
public class AuthService {
	
	@Autowired
	AuthMapper authMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserAuthProvider userAuthProvider;
	
	@Autowired
	UserService userService;
	
	public ResponseEntity<Object> login(CredentialDto credentialDto) {
		UserEntity user = userService.findByLogin(credentialDto.getLogin());
		if(user!=null && passwordEncoder.matches(credentialDto.getPassword(), user.getPassword() )) {
			UserDto userDto = user.toUserDto();
			userDto.setToken(userAuthProvider.createToken(credentialDto.getLogin()));
			return ResponseEntity.ok(userDto);
		}
		return new ApiExceptions("username or password does not mathch", HttpStatus.BAD_REQUEST).response();
	}
	
	public ResponseEntity<Object> register(SignUpDto signUpDto) {
		UserEntity user = userService.findByLogin(signUpDto.getLogin());
		if(user==null) {
			System.out.println(signUpDto);
			signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
			try {
				authMapper.register(signUpDto);
				UserDto registeredUserDto = userService.findByLogin(signUpDto.getLogin()).toUserDto();
				registeredUserDto.setToken(userAuthProvider.createToken(signUpDto.getLogin()));
				return new ResponseEntity<Object>(registeredUserDto,HttpStatus.CREATED);
			}catch(Exception e) {
				return new ApiExceptions("your register cannot be complete", HttpStatus.CONFLICT).response();
			}
		}
		return new ApiExceptions("your register cannot be complete", HttpStatus.CONFLICT).response();
	}
}
