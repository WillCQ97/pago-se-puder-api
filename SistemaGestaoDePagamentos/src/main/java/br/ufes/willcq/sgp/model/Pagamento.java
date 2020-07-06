package br.ufes.willcq.sgp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "pagamentos")
@JsonInclude(Include.NON_EMPTY)
public class Pagamento {

	@Column(name = "id_pagamento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Basic(optional = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "A data de vencimento é necesária.")
	private Date dataVencimento;

	@Basic(optional = false)
	@NotBlank(message = "A descricao é necessária.")
	private String descricao;

	@Basic(optional = false)
	@NotNull(message = "O valor é necessário.")
	private double valor;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAprovacao;

	@JoinColumn(name = "id_solicitante")
	@ManyToOne
	@NotNull
	private Funcionario solicitante;

	@JoinColumn(name = "id_aprovador")
	@ManyToOne
	private Funcionario aprovador;

	@OneToMany(mappedBy = "codigoPagamento")
	@Valid
	private List<DetalhePagamento> detalhes;

	public Pagamento() {
		detalhes = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public Funcionario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Funcionario solicitante) {
		this.solicitante = solicitante;
	}

	public Funcionario getAprovador() {
		return aprovador;
	}

	public void setAprovador(Funcionario aprovador) {
		this.aprovador = aprovador;
	}

	public List<DetalhePagamento> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<DetalhePagamento> detalhes) {
		this.detalhes = detalhes;
	}

}
