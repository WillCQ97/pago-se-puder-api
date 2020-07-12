package br.ufes.willcq.sgp.api.dto;

import javax.validation.constraints.NotNull;

public class FuncionarioIdInput {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
