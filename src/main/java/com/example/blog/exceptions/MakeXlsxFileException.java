package com.example.blog.exceptions;

public class MakeXlsxFileException extends RuntimeException {
	private static final long serialVersionUID = 8198887922000466666L;
	
	public MakeXlsxFileException(String message) {
		super(message);
	}
}