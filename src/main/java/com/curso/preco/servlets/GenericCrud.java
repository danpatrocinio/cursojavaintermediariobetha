package com.curso.preco.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso.preco.model.Entity;
import com.curso.preco.model.EntityList;
import com.curso.preco.model.repositories.GenericRepository;

public abstract class GenericCrud<E extends Entity> extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected GenericRepository<E> repository;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();

		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {

			E entity = repository.getById(Long.parseLong(request.getParameter("id")));
			writer.append(entity.toJson());
		} else {

			List<E> list = repository.getAll();

			EntityList json = new EntityList();
			if (list != null && !list.isEmpty()) {
				list.forEach(json);
			}

			writer.append(json.toString());
		}

		writer.flush();
		writer.close();
	}

}
