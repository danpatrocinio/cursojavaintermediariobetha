package com.curso.preco.model.repositories;

import java.util.*;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.entities.Combustiveis;

public class CombustiveisRepository extends GenericRepository<Combustiveis> {

	private static final Map<String, Boolean> unidades = new HashMap<String, Boolean>();
	static {
		CombustiveisRepository.unidades.put("LT", true);
		CombustiveisRepository.unidades.put("M3", true);
	}

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
		c.setDescricao("Combust�vel " + id);
		c.setUnidadeMedida("LT");
		c.setVersion(System.currentTimeMillis());
		return c;
	}

	@Override
	public Combustiveis save(Combustiveis entity) throws RepositoryException {

		validade(entity);

		return super.save(entity);
	}

	@Override
	public Combustiveis update(Combustiveis entity) throws RepositoryException {

		validade(entity);

		return super.save(entity);
	}

	private void validade(Combustiveis c) throws RepositoryException {
		if (c.getDescricao() == null) {
			throw new RepositoryException("A descri��o do combust�vel deve ser informada!");
		}
		if (c.getUnidadeMedida() == null) {
			throw new RepositoryException("A unidade de medida do combust�vel deve ser informada!");
		}
		if (!CombustiveisRepository.unidades.containsKey(c.getUnidadeMedida().toUpperCase())) {
			throw new RepositoryException("Unidade de medida do combust�vel inv�lida!");
		}
	}
}
