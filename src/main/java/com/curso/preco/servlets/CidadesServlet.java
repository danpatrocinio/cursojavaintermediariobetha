package com.curso.preco.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.curso.preco.model.Cidades;
import com.curso.preco.model.repositories.CidadesRepository;

@WebServlet("/cidades")
public class CidadesServlet extends GenericCrud<Cidades> {

	private static final long serialVersionUID = 1275575235803839338L;

	@Override
	public void init() throws ServletException {
		repositoryClass = CidadesRepository.class;
		entityClass = Cidades.class;
	}

}
