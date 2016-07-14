package com.curso.preco.model.repositories;

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
import com.curso.preco.model.entities.ViewPrecos;

public class ViewPrecosRepository extends GenericRepository<ViewPrecos> {

	public ViewPrecosRepository() {
	}

	@Override
	public void delete(Class<Entity> classe, Long id) throws RepositoryException {
		throw new RepositoryException("Operação não permitida!");
	}

	@Override
	public List<Entity> getAll(Class<Entity> classe) throws RepositoryException {
		try {
			List<Entity> precos = new ArrayList<Entity>();

			Statement stm = Connection.get().getStm();

			ResultSet rs = stm.executeQuery(
			        "SELECT id_posto as idPosto, nome_posto as nomePosto, id_bandeira as idBandeira, nome_bandeira as nomeBandeira, cidade, endereco, combustivel, preco, data FROM vw_precos");

			while (rs.next()) {
				precos.add(new ViewPrecos().fromResultSet(rs));
			}

			return precos;

		} catch (SQLException ex) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, ex);
			throw new RepositoryException("Erro ao recuperar lista do banco.", ex);
		} catch (ParseableException e) {
			Logger.getLogger(PostosRepository.class.getName()).log(Level.SEVERE, null, e);
			throw new RepositoryException("Erro ao recuperar lista do banco.", e);
		}
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
