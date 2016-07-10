package com.curso.preco.model.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.Cidades;

public class CidadesRepository extends GenericRepository<Cidades> {

	public CidadesRepository() {
	}

	@Override
	public List<Cidades> getAll() {
		return new ArrayList<>(Arrays.asList(getById(1L), getById(2L), getById(3L)));
	}

	@Override
	public Cidades getById(Long id) {

		Cidades c = new Cidades();
		c.setId(id);
		c.setNome("Cidade " + id);
		c.setUf("SC");
		c.setVersion(System.currentTimeMillis());
		return c;
	}

	@Override
	public Cidades save(Cidades entity) throws RepositoryException {

		validate(entity);

		return super.save(entity);
	}

	@Override
	public Cidades update(Cidades entity) throws RepositoryException {

		validate(entity);

		return super.update(entity);
	}

	private void validate(Cidades c) throws RepositoryException {
		if (c.getNome() == null) {
			throw new RepositoryException("O nome da cidade deve ser informado!");
		}
	}
}
