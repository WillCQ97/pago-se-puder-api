package br.ufes.willcq.sgp.api.dto;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetalhePagamentoDTO {

	@Id
	@NotNull
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;

	@NotBlank
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
