package com.curso.preco.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.curso.preco.exceptions.ParseableException;
import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.jdbc.Connection;
import com.curso.preco.model.Entity;
import com.curso.preco.model.entities.Postos;

public class PostosRepository extends GenericRepository<Postos> {

	public PostosRepository() {
	}

	@Override
	public List<Entity> getAll(Class classe) throws RepositoryException {
		try {
			List<Entity> list = new ArrayList<Entity>();

			Statement stm = Connection.get().getStm();

			ResultSet rs = stm.executeQuery(
			        "SELECT id, id_bandeira as idBandeira, id_cidade as idCidade, nome, endereco, complemento, numero, version FROM postos");

			while (rs.next()) {
				list.add(new Postos().fromResultSet(rs));
			}

			return list;

		} catch (SQLException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao recuperar lista do banco.", ex);
		} catch (ParseableException e) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		}
	}

	@Override
	public Postos getById(Class<Entity> classe, Long id) throws RepositoryException {
		try {

			if (id == null) {
				throw new RepositoryException(
				        message(GenericRepository.MSG_PARAMETER_MISSING, "id"));
			}

			PreparedStatement stm = Connection.get().getParamStm(
			        "SELECT id, id_bandeira as idBandeira, id_cidade as idCidade,nome, endereco, complemento, numero, version FROM postos WHERE id = ?");
			stm.setLong(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return (Postos) new Postos().fromResultSet(rs);
			}

		} catch (SQLException | ParseableException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao buscar o registro", ex);
		}

		return null;
	}

	public List<Postos> getByNome(String nome) throws RepositoryException {
		try {

			if (nome == null) {
				throw new RepositoryException(
				        message(GenericRepository.MSG_PARAMETER_MISSING, "nome"));
			}

			List<Postos> postos = new ArrayList<Postos>();

			PreparedStatement stm = Connection.get().getParamStm(
			        "SELECT id, id_bandeira as idBandeira, id_cidade as idCidade, nome, endereco, complemento, numero, version FROM postos WHERE nome like ? limit 10");
			stm.setString(1, "%" + nome.toUpperCase() + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				postos.add((Postos) new Postos().fromResultSet(rs));
			}
			return postos;
		} catch (SQLException | ParseableException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao recuperar lista do banco.", ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Postos save(Postos posto) throws RepositoryException {
		try {
			validate(posto);

			String sql = "INSERT INTO postos (id_bandeira, id_cidade, nome, endereco, complemento, numero) VALUES (?, ?, ?, ?, ?, ?)";
			if (posto.getIdBandeira() == null) {
				sql = sql.replace("id_bandeira,", "").replace("(?, ", "(");
			}
			if (posto.getIdCidade() == null) {
				sql = sql.replace("id_cidade,", "").replace("(?, ", "(");
			}
			PreparedStatement stm = Connection.get().getParamStm(sql,
			        Statement.RETURN_GENERATED_KEYS);

			int field = 0;
			if (posto.getIdBandeira() != null) {
				stm.setLong(++field, posto.getIdBandeira());
			}
			if (posto.getIdCidade() != null) {
				stm.setLong(++field, posto.getIdCidade());
			}
			stm.setString(++field, posto.getNome());
			stm.setString(++field, posto.getEndereco());
			stm.setString(++field, posto.getComplemento());
			stm.setString(++field, posto.getNumero());
			stm.execute();

			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				return getById((Class<Entity>) posto.getClass(), rs.getLong(1));
			}

		} catch (SQLException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Não foi possível inserir registro!", ex);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Postos update(Postos entity) throws RepositoryException {

		try {
			validate(entity);
			PreparedStatement stm = Connection.get().getParamStm(
			        "UPDATE postos SET nome = ?, id_bandeira = ?, id_cidade = ?, endereco = ?, numero = ?, complemento = ? WHERE id = ?");
			stm.setString(1, entity.getNome());
			stm.setLong(2, entity.getIdBandeira());
			stm.setLong(3, entity.getIdCidade());
			stm.setString(4, entity.getEndereco());
			stm.setString(5, entity.getNumero());
			stm.setString(6, entity.getComplemento());
			stm.setLong(7, entity.getId());
			stm.execute();

			return getById((Class<Entity>) entity.getClass(), entity.getId());

		} catch (SQLException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Não foi possível atualizar o registro", ex);
		}

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
