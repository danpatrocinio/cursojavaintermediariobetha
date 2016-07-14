package com.curso.preco.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.curso.preco.annotations.EntityTable;
import com.curso.preco.exceptions.ParseableException;
import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.jdbc.Connection;
import com.curso.preco.model.Entity;

public abstract class GenericRepository<E extends Entity> implements Repository<E> {

	public static final String MSG_DATA_MISSING = "Não foi possível recuperar os dados da solicitação!";
	public static final String MSG_INVALID_PARAMETER = "Parâmetro '%s' é inválido!";
	public static final String MSG_PARAMETER_MISSING = "O parâmetro '%s' deve ser informado!";

	public GenericRepository() {
	}

	@Override
	public void delete(Class<Entity> classe, Long id) throws RepositoryException {
		if (id == null) {
			throw new RepositoryException(message(GenericRepository.MSG_PARAMETER_MISSING, "id"));
		}
		try {
			PreparedStatement stm = Connection.get().getParamStm("DELETE FROM "
			        + classe.getAnnotation(EntityTable.class).name() + " WHERE id = ?");
			stm.setLong(1, id);
			stm.execute();
		} catch (SQLException ex) {
			Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Não foi possível excluir o registro!", ex);
		}
	}

	@Override
	public List<Entity> getAll(Class<Entity> classe) throws RepositoryException {

		List<Entity> list = new ArrayList<Entity>();

		try {
			Statement stm = Connection.get().getStm();
			ResultSet rs = stm.executeQuery(
			        "SELECT * FROM " + classe.getAnnotation(EntityTable.class).name());

			while (rs.next()) {
				list.add(classe.newInstance().fromResultSet(rs));
			}
			return list;
		} catch (SQLException ex) {
			Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao recuperar lista do banco.", ex);
		} catch (ParseableException e) {
			Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		} catch (InstantiationException e) {
			Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		} catch (IllegalAccessException e) {
			Logger.getLogger(GenericRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getById(Class<Entity> classe, Long id) throws RepositoryException {
		if (id == null) {
			throw new RepositoryException(message(GenericRepository.MSG_PARAMETER_MISSING, "id"));
		}

		try {

			PreparedStatement stm = Connection.get().getParamStm("SELECT * FROM "
			        + classe.getAnnotation(EntityTable.class).name() + " WHERE id = ?");
			stm.setLong(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return (E) classe.newInstance().fromResultSet(rs);
			}

		} catch (SQLException | ParseableException | InstantiationException
		        | IllegalAccessException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao buscar o registro", ex);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E save(Entity entity) throws RepositoryException {
		if (entity == null) {
			throw new RepositoryException(message(GenericRepository.MSG_DATA_MISSING));
		}
		entity.setVersion(System.currentTimeMillis());
		return (E) entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E update(Entity entity) throws RepositoryException {
		if (entity == null) {
			throw new RepositoryException(message(GenericRepository.MSG_DATA_MISSING));
		}
		entity.setVersion(System.currentTimeMillis());
		return (E) entity;
	}

}
