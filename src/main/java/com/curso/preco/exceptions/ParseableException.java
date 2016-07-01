package com.curso.preco.exceptions;

public class ParseableException extends Exception {

	private static final long serialVersionUID = 7485564119645273267L;

	public ParseableException(String message) {
		super(message);
	}

	public ParseableException(String message, Throwable e) {
		super(message, e);
	}

}
