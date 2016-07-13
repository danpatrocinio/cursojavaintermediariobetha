package com.curso.preco.model.repositories;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.entities.PostosPrecos;

public class PostosPrecosRepository extends GenericRepository<PostosPrecos> {

	public PostosPrecosRepository() {
	}

	@Override
	public PostosPrecos save(PostosPrecos entity) throws RepositoryException {

		validate(entity);

		return super.save(entity);
	}

	@Override
	public PostosPrecos update(PostosPrecos entity) throws RepositoryException {

		validate(entity);

		return super.save(entity);
	}

	private void validate(PostosPrecos pp) throws RepositoryException {
		if (pp == null) {
			throw new RepositoryException("");
		}
		if (pp.getPreco() == null) {
			throw new RepositoryException("O preço deve ser informado!");
		}
		if (pp.getIdPosto() == null) {
			throw new RepositoryException("O posto deve ser informador!");
		}
		if (pp.getIdCombustivel() == null) {
			throw new RepositoryException("O tipo do combustível deve ser informado!");
		}
	}
}
