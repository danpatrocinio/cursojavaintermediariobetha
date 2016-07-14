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
import com.curso.preco.model.entities.PostosPrecos;

public class PostosPrecosRepository extends GenericRepository<PostosPrecos> {

	public PostosPrecosRepository() {
	}

	@Override
	public List<Entity> getAll(Class<Entity> classe) throws RepositoryException {
		try {
			List<Entity> list = new ArrayList<Entity>();

			Statement stm = Connection.get().getStm();

			ResultSet rs = stm.executeQuery(
			        "SELECT postos_precos.id, id_posto as idPosto, nome as nomePosto, id_combustivel as idCombustivel, descricao as descricaoCombustivel, preco, dh_data as dhData, postos_precos.version FROM postos_precos JOIN postos on (postos.id=postos_precos.id_posto) JOIN combustiveis on (combustiveis.id=postos_precos.id_combustivel)");

			while (rs.next()) {
				list.add(new PostosPrecos().fromResultSet(rs));
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
	public PostosPrecos getById(Class<Entity> classe, Long id) throws RepositoryException {
		try {

			if (id == null) {
				throw new RepositoryException(
				        message(GenericRepository.MSG_PARAMETER_MISSING, "id"));
			}

			PreparedStatement stm = Connection.get().getParamStm(
			        "SELECT postos_precos.id, id_posto as idPosto, nome as nomePosto, id_combustivel as idCombustivel, descricao as descricaoCombustivel, preco, dh_data as dhData, postos_precos.version FROM postos_precos JOIN postos on (postos.id=postos_precos.id_posto) JOIN combustiveis on (combustiveis.id=postos_precos.id_combustivel) WHERE postos_precos.id = ?");
			stm.setLong(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return (PostosPrecos) new PostosPrecos().fromResultSet(rs);
			}

		} catch (SQLException | ParseableException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao buscar o registro", ex);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PostosPrecos save(PostosPrecos preco) throws RepositoryException {

		try {
			validate(preco);

			String sql = "INSERT INTO postos_precos (id_posto, id_combustivel, preco, dh_data) VALUES (?, ?, ?, ?)";
			if (preco.getIdPosto() == null) {
				sql = sql.replace("id_posto,", "").replace("(?, ", "(");
			}
			if (preco.getIdCombustivel() == null) {
				sql = sql.replace("id_combustivel,", "").replace("(?, ", "(");
			}
			PreparedStatement stm = Connection.get().getParamStm(sql,
			        Statement.RETURN_GENERATED_KEYS);

			int field = 0;
			if (preco.getIdPosto() != null) {
				stm.setLong(++field, preco.getIdPosto());
			}
			if (preco.getIdCombustivel() != null) {
				stm.setLong(++field, preco.getIdCombustivel());
			}
			stm.setBigDecimal(++field, preco.getPreco());
			stm.setTimestamp(++field, preco.getDhData());
			stm.execute();

			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				return getById((Class<Entity>) preco.getClass(), rs.getLong(1));
			}

		} catch (SQLException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Não foi possível inserir registro!", ex);
		}
		return null;

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
