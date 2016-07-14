package com.curso.preco.model.repositories;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.entities.Bandeiras;

public class BandeirasRepository extends GenericRepository<Bandeiras> {

	public BandeirasRepository() {
	}

	@Override
	public Bandeiras save(Bandeiras entity) throws RepositoryException {

		validate(entity);

		return super.save(entity);
	}

	@Override
	public Bandeiras update(Bandeiras entity) throws RepositoryException {

		validate(entity);

		return super.update(entity);
	}

	private void validate(Bandeiras b) throws RepositoryException {
		if (b.getNome() == null) {
			throw new RepositoryException("O nome da bandeira deve ser informado!");
		}
	}
}
