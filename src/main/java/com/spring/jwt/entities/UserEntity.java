package com.spring.jwt.entities;

import com.spring.jwt.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserEntity {
	private int userId;
	private String login;
	private String password;
	private String role;
	
	public UserDto toUserDto() {
		UserDto userDto = new UserDto();
		userDto.setId(userId);
		userDto.setLogin(login);
		userDto.setRole(role);
		return userDto;
	}
}
