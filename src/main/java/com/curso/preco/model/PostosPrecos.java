package com.curso.preco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class PostosPrecos implements Entity, Serializable {

	private static final long serialVersionUID = 5064002532216650238L;
	private Timestamp dhData;
	private Long id;
	private Long idCombustivel;
	private Long idPosto;
	private BigDecimal preco;
	private Long version;

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

	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public Long getVersion() {
		return version;
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

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
		version = systemCurrentTimeMillis;
	}

}
