package com.curso.preco.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso.preco.exceptions.ParseableException;
import com.curso.preco.exceptions.RepositoryException;
import com.curso.preco.model.Entity;
import com.curso.preco.model.EntityList;
import com.curso.preco.model.repositories.GenericRepository;
import com.curso.preco.model.repositories.Repository;

public abstract class GenericCrud<E extends Entity> extends HttpServlet {

	private static final String MSG_INTERNAL_ERROR = "Erro interno ao processar solicitação";
	private static final long serialVersionUID = 1L;
	protected Class entityClass;
	protected Class repositoryClass;
	private E entity;
	private GenericRepository<E> repository;

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {

			try {

				getRepository().delete(Long.parseLong(request.getParameter("id")));

			} catch (NumberFormatException e) {
				treatedError(response, GenericRepository.MSG_INVALID_PARAMETER,
				        request.getParameter("id"));
				return;
			} catch (RepositoryException e) {
				treatedError(response, e.getMessage());
				return;
			}

		} else {
			treatedError(response, GenericRepository.MSG_PARAMETER_MISSING, "id");
			return;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();

		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {

			try {

				entity = getRepository().getById(Long.parseLong(request.getParameter("id")));
				writer.append(entity.toJson());

			} catch (NumberFormatException e) {
				treatedError(response, GenericRepository.MSG_INVALID_PARAMETER,
				        request.getParameter("id"));
				return;
			} catch (RepositoryException e) {
				treatedError(response, e.getMessage());
				return;
			} catch (ParseableException e) {
				internalError(response, e);
				return;
			}
		} else {

			try {

				List<E> list = getRepository().getAll();

				EntityList json = new EntityList();

				if (list != null && !list.isEmpty()) {
					list.forEach(json);
				}

				writer.append(json.toString());

			} catch (RepositoryException e) {
				treatedError(response, e.getMessage());
				return;
			}
		}

		writer.flush();
		writer.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		Map<String, Object> formData = new HashMap<>();
		for (String key : request.getParameterMap().keySet()) {
			formData.put(key, request.getParameter(key));
		}

		if (!formData.isEmpty()) {

			try {
				entity = (E) getNewEntity().fromMap(formData);
			} catch (ParseableException e) {
				internalError(response, e);
				return;
			}

		} else {

			StringBuffer jb = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null) {
					jb.append(line);
				}
			} catch (Exception e) {
				treatedError(response, e.getMessage());
				return;
			}

			try {

				entity = (E) getNewEntity().fromJson(jb.toString());

			} catch (ParseableException e) {
				internalError(response, e);
				return;
			}

		}

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			if (entity != null && entity.getId() == null) {
				entity = getRepository().save(entity);
				writer.append(entity.toJson());
			} else if (entity != null) {
				entity = getRepository().update(entity);
				writer.append(entity.toJson());
			}

			writer.flush();
			writer.close();

		} catch (RepositoryException e) {
			treatedError(response, e.getMessage());
		} catch (ParseableException e) {
			internalError(response, e);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	protected Repository<E> getRepository() {
		if (repository == null) {
			try {
				repository = (GenericRepository<E>) repositoryClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return repository;
	}

	@SuppressWarnings("unchecked")
	private E getNewEntity() {

		try {
			entity = (E) entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return entity;
	}

	private void internalError(HttpServletResponse response, Exception e) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.append(GenericCrud.MSG_INTERNAL_ERROR);
		writer.flush();
		writer.close();
		e.printStackTrace();
	}

	private void treatedError(HttpServletResponse response, String error, Object... args)
	        throws IOException {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.append(String.format(error, args));
		writer.flush();
		writer.close();
	}

}
