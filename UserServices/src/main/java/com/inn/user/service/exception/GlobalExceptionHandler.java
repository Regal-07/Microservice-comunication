package com.inn.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inn.user.service.payload.ApiResponse;

@RestControllerAdvice	
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundEx.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundEx(ResourceNotFoundEx ex){
		String msg = ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(msg).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
}