package com.curso.preco.model.repositories;

import java.util.List;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.Entity;

public abstract class GenericRepository<E extends Entity> implements Repository<E> {

	public static final String MSG_DATA_MISSING = "Não foi possível recuperar os dados da solicitação!";
	public static final String MSG_INVALID_PARAMETER = "Parâmetro '%s' é inválido!";
	public static final String MSG_PARAMETER_MISSING = "O parâmetro '%s' deve ser informado!";

	public GenericRepository() {
	}

	@Override
	public void delete(Long id) throws RepositoryException {
		if (id == null) {
			throw new RepositoryException(message(GenericRepository.MSG_PARAMETER_MISSING, "id"));
		}
	}

	@Override
	public List<E> getAll() throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getById(Long id) throws RepositoryException {
		if (id == null) {
			throw new RepositoryException(message(GenericRepository.MSG_PARAMETER_MISSING, "id"));
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E save(Entity entity) throws RepositoryException {
		if (entity == null) {
			throw new RepositoryException(message(GenericRepository.MSG_DATA_MISSING));
		}
		entity.setVersion(System.currentTimeMillis());
		entity.setId(1L);
		return (E) entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E update(Entity entity) throws RepositoryException {
		if (entity == null) {
			throw new RepositoryException(message(GenericRepository.MSG_DATA_MISSING));
		}
		entity.setVersion(System.currentTimeMillis());
		return (E) entity;
	}

}
