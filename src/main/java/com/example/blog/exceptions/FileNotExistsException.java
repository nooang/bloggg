package com.example.blog.exceptions;

public class FileNotExistsException extends RuntimeException {
	private static final long serialVersionUID = 1649821416356242134L;
	
	public FileNotExistsException(String message) {
		super(message);
	}
}
