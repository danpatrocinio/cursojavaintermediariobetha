package com.curso.preco.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.curso.preco.model.Postos;
import com.curso.preco.model.repositories.PostosRepository;

@WebServlet("/postos")
public class PostosServlet extends GenericCrud<Postos> {

	private static final long serialVersionUID = -8334085951067959268L;

	@Override
	public void init() throws ServletException {
		repository = new PostosRepository();
	}

}
