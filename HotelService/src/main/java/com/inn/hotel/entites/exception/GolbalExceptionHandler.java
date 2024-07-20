package com.inn.hotel.entites.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GolbalExceptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Map<String,Object>> notFoundHandler(ResourceNotFound ex){
		Map<String,Object> map = new HashMap<>();
		map.put("message",ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
}
