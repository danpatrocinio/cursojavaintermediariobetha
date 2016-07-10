package com.curso.preco.model.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.Postos;

public class PostosRepository extends GenericRepository<Postos> {

	public PostosRepository() {
	}

	@Override
	public List<Postos> getAll() {
		return new ArrayList<>(Arrays.asList(getById(1L), getById(2L), getById(3L)));
	}

	@Override
	public Postos getById(Long id) {

		Postos p = new Postos();
		p.setId(id);
		p.setNome("Postos " + id);
		p.setEndereco("Rua " + id + " de Maio");
		p.setIdBandeira(2L * id);
		p.setNumero("193");
		p.setDetalheEndereco("Ao lado da esquina " + id + " de Maio");
		p.setVersion(System.currentTimeMillis());
		return p;
	}

	@Override
	public Postos save(Postos entity) throws RepositoryException {

		validate(entity);

		return super.save(entity);
	}

	@Override
	public Postos update(Postos entity) throws RepositoryException {

		validate(entity);

		return super.save(entity);
	}

	private void validate(Postos p) throws RepositoryException {
		if (p == null) {
			throw new RepositoryException("");
		}
		if (p.getNome() == null) {
			throw new RepositoryException("O nome do posto deve ser informado!");
		}
	}
}
