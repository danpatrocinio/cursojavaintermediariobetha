package com.curso.preco.model.entities;

import java.io.Serializable;

import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.model.Entity;

public class Cidades implements Entity, Serializable {

	@IgnoreOnParseable
	private static final long serialVersionUID = 598471292690269897L;

	private Long codigo;
	private String nome;
	private String uf;

	public Cidades() {
	}

	public Long getCodigo() {
		return codigo;
	}

	@Override
	public Long getId() {
		return codigo;
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

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public void setId(Long codigo) {
		this.codigo = codigo;
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
