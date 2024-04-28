package com.example.blog.exceptions;

public class PageNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 3981862568748096917L;
	
	public PageNotFoundException(String message) {
		super(message);
	}
}
