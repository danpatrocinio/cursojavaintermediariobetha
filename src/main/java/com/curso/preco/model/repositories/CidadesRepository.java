package com.curso.preco.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.jdbc.Connection;
import com.curso.preco.model.entities.Cidades;

public class CidadesRepository extends GenericRepository<Cidades> {

	public CidadesRepository() {
	}

	@Override
	public void delete(Long id) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}

	@Override
	public List<Cidades> getAll() throws RepositoryException {
		try {
			List<Cidades> cidades = new ArrayList<Cidades>();

			Statement stm = Connection.get().getStm();
			ResultSet rs = stm.executeQuery("SELECT * FROM cidades");

			while (rs.next()) {
				cidades.add(parse(rs));
			}
			return cidades;
		} catch (SQLException ex) {
			Logger.getLogger(CidadesRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao recuperar lista do banco.", ex);
		}
	}

	@Override
	public Cidades getById(Long codigo) throws RepositoryException {
		try {

			if (codigo == null) {
				throw new RepositoryException(
				        message(GenericRepository.MSG_PARAMETER_MISSING, "id"));
			}

			PreparedStatement stm = Connection.get()
			        .getParamStm("SELECT * FROM cidades WHERE codigo = ?");
			stm.setLong(1, codigo);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return parse(rs);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CidadesRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao buscar o registro", ex);
		}

		return null;
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
				cidades.add(parse(rs));
			}
			return cidades;
		} catch (SQLException ex) {
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

	private Cidades parse(ResultSet rs) throws SQLException {
		Cidades c = new Cidades();
		c.setId(rs.getLong("codigo"));
		c.setNome(rs.getString("nome"));
		c.setUf(rs.getString("uf"));
		return c;
	}

}
