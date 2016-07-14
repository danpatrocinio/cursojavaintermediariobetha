package com.curso.preco.model.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.curso.preco.annotations.EntityTable;
import com.curso.preco.exceptions.ParseableException;
import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.jdbc.Connection;
import com.curso.preco.model.Entity;
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
	public List<Entity> getAll(Class<Entity> classe) throws RepositoryException {
		List<Entity> list = new ArrayList<Entity>();

		try {
			Statement stm = Connection.get().getStm();
			ResultSet rs = stm.executeQuery(
			        "SELECT id, descricao, unidade_medida as unidadeMedida, version FROM "
			                + classe.getAnnotation(EntityTable.class).name());

			while (rs.next()) {
				list.add(classe.newInstance().fromResultSet(rs));
			}
			return list;
		} catch (SQLException ex) {
			Logger.getLogger(CombustiveisRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao recuperar lista do banco.", ex);
		} catch (ParseableException e) {
			Logger.getLogger(CombustiveisRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		} catch (InstantiationException e) {
			Logger.getLogger(CombustiveisRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		} catch (IllegalAccessException e) {
			Logger.getLogger(CombustiveisRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		}
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
			throw new RepositoryException("A descrição do combustível deve ser informada!");
		}
		if (c.getUnidadeMedida() == null) {
			throw new RepositoryException("A unidade de medida do combustível deve ser informada!");
		}
		if (!CombustiveisRepository.unidades.containsKey(c.getUnidadeMedida().toUpperCase())) {
			throw new RepositoryException("Unidade de medida do combustível inválida!");
		}
	}
}
