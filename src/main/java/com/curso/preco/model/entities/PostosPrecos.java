package com.curso.preco.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.curso.preco.annotations.EntityTable;
import com.curso.preco.annotations.IgnoreOnParseable;
import com.curso.preco.model.Entity;

@EntityTable(name = "postos_precos")
public class PostosPrecos implements Entity, Serializable {

	@IgnoreOnParseable
	private static final long serialVersionUID = 5064002532216650238L;
	private String descricaoCombustivel;
	private Timestamp dhData;
	private Long id;
	private Long idCombustivel;
	private Long idPosto;
	private String nomePosto;
	private BigDecimal preco;
	private Long version;

	public String getDescricaoCombustivel() {
		return descricaoCombustivel;
	}

	public Timestamp getDhData() {
		return dhData;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Long getIdCombustivel() {
		return idCombustivel;
	}

	public Long getIdPosto() {
		return idPosto;
	}

	public String getNomePosto() {
		return nomePosto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	public void setDescricaoCombustivel(String descricaoCombustivel) {
		this.descricaoCombustivel = descricaoCombustivel;
	}

	public void setDhData(Timestamp dhData) {
		this.dhData = dhData;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setIdCombustivel(Long idCombustivel) {
		this.idCombustivel = idCombustivel;
	}

	public void setIdPosto(Long idPosto) {
		this.idPosto = idPosto;
	}

	public void setNomePosto(String nomePosto) {
		this.nomePosto = nomePosto;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
		version = systemCurrentTimeMillis;
	}

}
