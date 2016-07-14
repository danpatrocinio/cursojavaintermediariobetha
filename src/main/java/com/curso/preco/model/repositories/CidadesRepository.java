package com.curso.preco.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.curso.preco.exceptions.ParseableException;
import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.jdbc.Connection;
import com.curso.preco.model.Entity;
import com.curso.preco.model.entities.Cidades;

public class CidadesRepository extends GenericRepository<Cidades> {

	public CidadesRepository() {
	}

	@Override
	public void delete(Class<Entity> classe, Long id) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}

	public List<Cidades> getByNome(String nome) throws RepositoryException {
		try {

			if (nome == null) {
				throw new RepositoryException(
				        message(GenericRepository.MSG_PARAMETER_MISSING, "nome"));
			}

			List<Cidades> cidades = new ArrayList<Cidades>();

			PreparedStatement stm = Connection.get()
			        .getParamStm("SELECT * FROM cidades WHERE nome like ? limit 10");
			stm.setString(1, "%" + nome.toUpperCase() + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				cidades.add((Cidades) new Cidades().fromResultSet(rs));
			}
			return cidades;
		} catch (SQLException | ParseableException ex) {
			Logger.getLogger(CidadesRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao recuperar lista do banco.", ex);
		}
	}

	@Override
	public Cidades save(Cidades entity) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}

	@Override
	public Cidades update(Cidades entity) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}

}
