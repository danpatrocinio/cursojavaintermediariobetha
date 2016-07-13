package com.curso.preco.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.curso.preco.model.entities.ViewPostos;
import com.curso.preco.model.repositories.ViewPostosRepository;

@WebServlet("/vwpostos")
public class ViewPostosServlet extends GenericCrud<ViewPostos> {

	private static final long serialVersionUID = 8847072680764016423L;

	@Override
	public void init() throws ServletException {
		repositoryClass = ViewPostosRepository.class;
		entityClass = ViewPostos.class;
	}

}
