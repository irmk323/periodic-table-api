package com.example.periodictableapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InputNotFoundException extends RuntimeException {
	
	public InputNotFoundException(String message) {
		super(message);
	}
}
