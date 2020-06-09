package br.ufes.willcq.sgp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetalhePagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int codigoPagamento;
	private Date data;
	private String descricao;
	private String usuario;
	
	public DetalhePagamento(int codigoPagamento, Date data, String descricao, String usuario) {
		super();
		this.codigoPagamento = codigoPagamento;
		this.data = data;
		this.descricao = descricao;
		this.usuario = usuario;
	}

	public int getCodigoPagamento() {
		return codigoPagamento;
	}

	public void setCodigoPagamento(int codigoPagamento) {
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return codigoPagamento + "," + formatoData.format(this.data) + "," + descricao + "," + usuario;
	}
	
}
