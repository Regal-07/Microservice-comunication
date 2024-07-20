package com.inn.hotel.entites.exception;

public class ResourceNotFound extends RuntimeException{

	public ResourceNotFound(String s) {
		super(s);
	}
	
	public ResourceNotFound() {
		super("Resource Not found");
	}
}
