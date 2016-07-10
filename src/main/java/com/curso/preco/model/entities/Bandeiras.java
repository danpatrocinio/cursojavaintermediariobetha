package com.curso.preco.model.entities;

import java.io.Serializable;

import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.model.Entity;

public class Bandeiras implements Entity, Serializable {

	@IgnoreOnParseable
	private static final long serialVersionUID = 598471292690269897L;

	private Long id;
	private String nome;
	private Long version;

	public Bandeiras() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
		version = systemCurrentTimeMillis;
	}

}
