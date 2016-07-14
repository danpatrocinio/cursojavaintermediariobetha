package com.curso.preco.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.EntityList;
import com.curso.preco.model.entities.Postos;
import com.curso.preco.model.repositories.GenericRepository;
import com.curso.preco.model.repositories.PostosRepository;

@WebServlet("/postos")
public class PostosServlet extends GenericCrud<Postos> {

	private static final long serialVersionUID = -8334085951067959268L;

	@Override
	public void init() throws ServletException {
		repositoryClass = PostosRepository.class;
		entityClass = Postos.class;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();

		if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {

			List<Postos> posto = new ArrayList<Postos>();
			PostosRepository rep = (PostosRepository) getRepository();

			try {
				posto = rep.getByNome(request.getParameter("nome"));
				if (posto != null && !posto.isEmpty()) {
					EntityList json = new EntityList();
					posto.forEach(json);
					String ret = json.toString();
					writer.append(ret);
					writer.flush();
					writer.close();
					return;
				}
			} catch (RepositoryException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.setCharacterEncoding("utf-8");
				writer.append(GenericRepository.MSG_DATA_MISSING);
				writer.flush();
				writer.close();
			}
		}

		super.doGet(request, response);
	}
}
