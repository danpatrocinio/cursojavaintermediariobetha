package com.curso.preco.model.entities;

import java.io.Serializable;

import com.curso.preco.annotations.EntityTable;
import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.model.Entity;

@EntityTable(name = "combustiveis")
public class Combustiveis implements Entity, Serializable {

	@IgnoreOnParseable
	private static final long serialVersionUID = -2351271283743490351L;

	private String descricao;
	private Long id;
	private String unidadeMedida;
	private Long version;

	public Combustiveis() {
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
		version = systemCurrentTimeMillis;
	}

}
