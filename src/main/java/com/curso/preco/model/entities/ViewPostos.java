package com.curso.preco.model.entities;

import java.io.Serializable;

import com.curso.preco.annotations.EntityTable;
import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.model.Entity;

@EntityTable(name = "vw_postos")
public class ViewPostos implements Entity, Serializable {

	@IgnoreOnParseable
	private static final long serialVersionUID = 3651465100047368338L;
	private String bandeira;
	private String endereco;
	private Long id;
	private String nome;
	private String numero;

	public String getBandeira() {
		return bandeira;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public Long getVersion() {
		return null;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
	}

}
