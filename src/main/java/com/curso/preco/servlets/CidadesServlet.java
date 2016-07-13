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
import com.curso.preco.model.entities.Cidades;
import com.curso.preco.model.repositories.CidadesRepository;
import com.curso.preco.model.repositories.GenericRepository;

@WebServlet("/cidades")
public class CidadesServlet extends GenericCrud<Cidades> {

	private static final long serialVersionUID = 1275575235803839338L;

	@Override
	public void init() throws ServletException {
		repositoryClass = CidadesRepository.class;
		entityClass = Cidades.class;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();

		if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()) {

			List<Cidades> cidades = new ArrayList<Cidades>();
			CidadesRepository rep = (CidadesRepository) getRepository();

			try {
				cidades = rep.getByNome(request.getParameter("nome"));
				if (cidades != null && !cidades.isEmpty()) {
					EntityList json = new EntityList();
					cidades.forEach(json);
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
