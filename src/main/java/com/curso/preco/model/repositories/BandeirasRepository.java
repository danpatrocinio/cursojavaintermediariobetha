package com.curso.preco.model.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.curso.preco.model.Bandeiras;

public class BandeirasRepository extends GenericRepository<Bandeiras> {

	public BandeirasRepository() {
	}

	@Override
	public List<Bandeiras> getAll() {
		return new ArrayList<>(Arrays.asList(getById(1L), getById(2L), getById(3L)));
	}

	@Override
	public Bandeiras getById(Long id) {

		Bandeiras b = new Bandeiras();
		b.setId(id);
		b.setNome("Bandeira " + id);
		b.setVersion(System.currentTimeMillis());
		return b;
	}
}
