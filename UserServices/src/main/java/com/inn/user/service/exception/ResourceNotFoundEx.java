package com.inn.user.service.exception;

public class ResourceNotFoundEx extends RuntimeException {

	public ResourceNotFoundEx() {
		super("Resouce Not Found");
	}

	public ResourceNotFoundEx(String msg) {
		super(msg);
	}
}