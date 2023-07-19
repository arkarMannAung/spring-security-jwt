package com.spring.jwt.dto;

import com.spring.jwt.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpDto {
	private String login;
	private String password;
	private String role;
	
	public UserEntity toUserEntity() {
		UserEntity user = new UserEntity();
		user.setLogin(login);
		user.setPassword(password);
		user.setRole(role);
		return user;
	}
}
