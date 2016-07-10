package com.curso.preco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ViewPrecos implements Entity, Serializable {

	private static final long serialVersionUID = 6328092706147151265L;

	private String cidade;
	private String combustivel;
	private Timestamp data;
	private String endereco;
	private Long idBandeira;
	private Long idPosto;
	private String nomeBandeira;
	private String nomePosto;
	private BigDecimal preco;

	public ViewPrecos() {
	}

	public String getCidade() {
		return cidade;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public Timestamp getData() {
		return data;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public Long getId() {
		return null;
	}

	public Long getIdBandeira() {
		return idBandeira;
	}

	public Long getIdPosto() {
		return idPosto;
	}

	public String getNomeBandeira() {
		return nomeBandeira;
	}

	public String getNomePosto() {
		return nomePosto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public Long getVersion() {
		return null;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public void setId(Long id) {
	}

	public void setIdBandeira(Long idBandeira) {
		this.idBandeira = idBandeira;
	}

	public void setIdPosto(Long idPosto) {
		this.idPosto = idPosto;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	public void setNomePosto(String nomePosto) {
		this.nomePosto = nomePosto;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public void setVersion(Long systemCurrentTimeMillis) {
	}

}
