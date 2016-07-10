package com.curso.preco.model.repositories;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.entities.PostosPrecos;

public class PostosPrecosRepository extends GenericRepository<PostosPrecos> {

	public PostosPrecosRepository() {
	}

	@Override
	public List<PostosPrecos> getAll() {
		return new ArrayList<>(Arrays.asList(getById(1L), getById(2L), getById(3L)));
	}

	@Override
	public PostosPrecos getById(Long id) {

		PostosPrecos p = new PostosPrecos();
		p.setId(id);
		p.setIdPosto(id + id);
		p.setPreco(new BigDecimal(3.45));
		p.setDhData(new Timestamp(System.currentTimeMillis()));
		p.setVersion(System.currentTimeMillis());
		return p;
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
