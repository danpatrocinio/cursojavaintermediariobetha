package com.curso.preco.exceptions;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 9054360958485078900L;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, Throwable e) {
		super(message, e);
	}

}
