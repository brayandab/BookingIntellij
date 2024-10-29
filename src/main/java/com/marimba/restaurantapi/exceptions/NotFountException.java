package com.marimba.restaurantapi.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.marimba.restaurantapi.dtos.ErrorDto;

public class NotFountException extends MarimbaException {

	private static final long serialVersionUID = 1L;

	public NotFountException(String code, String message) {
		super(code,HttpStatus.NOT_FOUND.value(), message);
		
	}
	
	public NotFountException(String code, String message, ErrorDto data) {
		super(code,HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
		
	}

}
