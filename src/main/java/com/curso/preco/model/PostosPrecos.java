package com.curso.preco.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PostosPrecos {

	private Timestamp dhData;
	private Long id;
	private Long idPosto;
	private BigDecimal preco;

	public Timestamp getDhData() {
		return dhData;
	}

	public Long getId() {
		return id;
	}

	public Long getIdPosto() {
		return idPosto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setDhData(Timestamp dhData) {
		this.dhData = dhData;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdPosto(Long idPosto) {
		this.idPosto = idPosto;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
