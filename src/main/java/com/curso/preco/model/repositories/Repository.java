package com.curso.preco.model.repositories;

import java.util.List;

import com.curso.preco.model.Entity;

public interface Repository<E extends Entity> {

	public List<E> getAll();

	public E getById(Long id);

	public void remove(E entity) throws Exception;

	public E save(E entity) throws Exception;

	public E update(E entity) throws Exception;

}
