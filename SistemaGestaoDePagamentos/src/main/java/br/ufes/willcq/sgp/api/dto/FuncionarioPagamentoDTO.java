package br.ufes.willcq.sgp.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FuncionarioPagamentoDTO {
	
	@NotNull
	private int id;
	
	@NotBlank
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
