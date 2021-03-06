package com.curso.preco.model.entities;

import java.io.Serializable;

import com.curso.preco.annotations.EntityTable;
import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.model.Entity;

@EntityTable(name = "cidades")
public class Cidades implements Entity, Serializable {

	@IgnoreOnParseable
	private static final long serialVersionUID = 598471292690269897L;

	private Long id;
	private String nome;
	private String uf;

	public Cidades() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getUf() {
		return uf;
	}

	@Override
	public Long getVersion() {
		return null;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
	}

}
