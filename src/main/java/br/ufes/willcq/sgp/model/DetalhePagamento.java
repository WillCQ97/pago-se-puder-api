package br.ufes.willcq.sgp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalhes")
public class DetalhePagamento {

	@Column(name = "id_detalhe")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(name = "id_pagamento")
	@NotNull
	private Long codigoPagamento;

	@NotNull(message = "A data é requerida.")
	private Date data;

	@NotBlank(message = "A descrição é requerida.")
	private String descricao;

	public DetalhePagamento() {
	}

	public DetalhePagamento(String descricao) {
		this.descricao = descricao;
	}

	public DetalhePagamento(Long codigoPagamento, Date data, String descricao) {
		super();
		this.codigoPagamento = codigoPagamento;
		this.data = data;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigoPagamento() {
		return codigoPagamento;
	}

	public void setCodigoPagamento(Long codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return codigoPagamento + "," + formatoData.format(this.data) + "," + descricao + ".";
	}

}
