package com.curso.preco.model.repositories;

import java.util.List;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.Entity;
import com.curso.preco.model.entities.ViewPostos;

public class ViewPostosRepository extends GenericRepository<ViewPostos> {

	public ViewPostosRepository() {
	}

	@Override
	public void delete(Class<Entity> classe, Long id) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}

	public List<ViewPostos> getByIdBandeira(Long idBandeira) {
		// TODO implementar 
		return null;
	}

	public List<ViewPostos> getByIdCidade(Long idCidade) {
		// TODO implementar 
		return null;
	}

	public List<ViewPostos> getByIdCombustivel(Long idCombustivel) {
		// TODO implementar 
		return null;
	}

	public List<ViewPostos> getByIdPosto(Long idPosto) {
		// TODO implementar 
		return null;
	}

	public List<ViewPostos> getByWithoudIdBandeira() {
		// TODO implementar 
		return null;
	}

	@Override
	public ViewPostos save(ViewPostos entity) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}

	@Override
	public ViewPostos update(ViewPostos entity) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}
}
