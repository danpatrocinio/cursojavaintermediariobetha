package com.curso.preco.model.entities;

import java.io.Serializable;

import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.model.Entity;

public class Postos implements Entity, Serializable {

	@IgnoreOnParseable
	private static final long serialVersionUID = 9067527960294783649L;
	private String detalheEndereco;
	private String endereco;
	private Long id;
	private Long idBandeira;
	private String nome;
	private String numero;
	private Long version;

	public String getDetalheEndereco() {
		return detalheEndereco;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Long getIdBandeira() {
		return idBandeira;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	public void setDetalheEndereco(String detalheEndereco) {
		this.detalheEndereco = detalheEndereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setIdBandeira(Long idBandeira) {
		this.idBandeira = idBandeira;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
		version = systemCurrentTimeMillis;
	}

}
