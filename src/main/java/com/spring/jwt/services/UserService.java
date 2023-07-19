package com.spring.jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jwt.entities.UserEntity;
import com.spring.jwt.repository.AuthMapper;

@Service
public class UserService {
	@Autowired
	AuthMapper authMapper;
 
	public UserEntity findByLogin(String login){
		return authMapper.findByLogin(login);
	}
}
