package com.curso.preco.model.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
