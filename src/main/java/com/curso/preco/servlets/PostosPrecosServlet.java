package com.curso.preco.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.curso.preco.model.PostosPrecos;
import com.curso.preco.model.repositories.PostosPrecosRepository;

@WebServlet("/precos")
public class PostosPrecosServlet extends GenericCrud<PostosPrecos> {

	private static final long serialVersionUID = -868866486343614593L;

	@Override
	public void init() throws ServletException {
		repositoryClass = PostosPrecosRepository.class;
		entityClass = PostosPrecos.class;
	}

}
