package com.curso.preco.model.repositories;

import com.curso.preco.exceptions.RepositoryException;

public interface Repository<E extends com.curso.preco.model.Entity> {

	public void delete(Long id) throws RepositoryException;

	public java.util.List<E> getAll() throws RepositoryException;

	public E getById(Long id) throws RepositoryException;

	public E save(E entity) throws RepositoryException;

	public E update(E entity) throws RepositoryException;

	default String message(String msg, Object... args) {
		return String.format(msg, args);
	}

}
