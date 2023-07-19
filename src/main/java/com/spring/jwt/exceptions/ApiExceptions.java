package com.spring.jwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ApiExceptions {

	private final String message;
	private final int code;
	private final HttpStatus status;

	public ApiExceptions(String message,HttpStatus status) {
		this.code = status.value();
		this.message = message;
		this.status = status;
	}
	
	public ApiExceptions(HttpStatus status,String message) {
		this.code = status.value();
		this.message = message;
		this.status = status;
	}
	
	public ResponseEntity<Object> response(){
		return new ResponseEntity<Object>(this,this.status);
	}

}
