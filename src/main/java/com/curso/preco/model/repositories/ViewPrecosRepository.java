package com.curso.preco.model.repositories;

import java.util.List;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.ViewPrecos;

public class ViewPrecosRepository extends GenericRepository<ViewPrecos> {

	public ViewPrecosRepository() {
	}

	@Override
	public void delete(Long id) throws RepositoryException {
	}

	@Override
	public List<ViewPrecos> getAll() {
		return null;
	}

	@Override
	public ViewPrecos getById(Long id) {
		return null;
	}

	public List<ViewPrecos> getByIdBandeira(Long idBandeira) {
		return null;
	}

	public List<ViewPrecos> getByIdCidade(Long idCidade) {
		return null;
	}

	public List<ViewPrecos> getByIdCombustivel(Long idCombustivel) {
		return null;
	}

	public List<ViewPrecos> getByIdPosto(Long idPosto) {
		return null;
	}

	public List<ViewPrecos> getByWithoudIdBandeira() {
		return null;
	}

	@Override
	public ViewPrecos save(ViewPrecos entity) throws RepositoryException {
		return null;
	}

	@Override
	public ViewPrecos update(ViewPrecos entity) throws RepositoryException {
		return null;
	}
}
