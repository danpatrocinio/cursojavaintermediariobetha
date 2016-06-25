package com.curso.preco.model.repositories;

import java.util.List;

import com.curso.preco.model.Entity;

public abstract class GenericRepository<E extends Entity> implements Repository<E> {

	public GenericRepository() {
	}

	@Override
	public List<E> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(E entity) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public E save(E entity) throws Exception {
		entity.setVersion(System.currentTimeMillis());
		return null;
	}

	@Override
	public E update(E entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
