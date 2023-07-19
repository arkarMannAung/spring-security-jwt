package com.spring.jwt.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spring.jwt.dto.SignUpDto;
import com.spring.jwt.entities.UserEntity;

@Mapper
public interface AuthMapper {
	public UserEntity findByLogin(@Param("login") String login);

	public void register(SignUpDto signUpDto);
}