package com.curso.preco.model.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.curso.preco.model.Combustiveis;

public class CombustiveisRepository extends GenericRepository<Combustiveis> {

	public CombustiveisRepository() {
	}

	@Override
	public List<Combustiveis> getAll() {
		return new ArrayList<>(Arrays.asList(getById(1L), getById(2L), getById(3L)));
	}

	@Override
	public Combustiveis getById(Long id) {

		Combustiveis c = new Combustiveis();
		c.setId(id);
		c.setDescricao("Combustível " + id);
		c.setUnidadeMedida("LT");
		c.setVersion(System.currentTimeMillis());
		return c;
	}
}
