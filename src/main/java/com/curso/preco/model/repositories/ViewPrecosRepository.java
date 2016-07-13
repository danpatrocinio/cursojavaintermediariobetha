package com.curso.preco.model.repositories;

import java.util.List;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.Entity;
import com.curso.preco.model.entities.ViewPrecos;

public class ViewPrecosRepository extends GenericRepository<ViewPrecos> {

	public ViewPrecosRepository() {
	}

	@Override
	public void delete(Class<Entity> classe, Long id) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
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
		throw new RepositoryException("Operação não permitida!");
	}

	@Override
	public ViewPrecos update(ViewPrecos entity) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}
}
