package com.curso.preco.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.curso.preco.model.ViewPrecos;
import com.curso.preco.model.repositories.ViewPrecosRepository;

@WebServlet("/vwprecos")
public class ViewPrecosServlet extends GenericCrud<ViewPrecos> {

	private static final long serialVersionUID = 8847072680764016423L;

	@Override
	public void init() throws ServletException {
		repositoryClass = ViewPrecosRepository.class;
		entityClass = ViewPrecos.class;
	}

}
