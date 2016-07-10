package com.curso.preco.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.curso.preco.model.Combustiveis;
import com.curso.preco.model.repositories.CombustiveisRepository;

@WebServlet("/combustiveis")
public class CombustiveisServlet extends GenericCrud<Combustiveis> {

	private static final long serialVersionUID = 5886798216892045364L;

	@Override
	public void init() throws ServletException {
		repositoryClass = CombustiveisRepository.class;
		entityClass = Combustiveis.class;
	}

}
