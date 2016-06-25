package com.curso.preco.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.curso.preco.model.Bandeiras;
import com.curso.preco.model.repositories.BandeirasRepository;

@WebServlet("/bandeiras")
public class BandeirasServlet extends GenericCrud<Bandeiras> {

	private static final long serialVersionUID = 8847072680764016423L;

	@Override
	public void init() throws ServletException {
		repository = new BandeirasRepository();
	}

}
