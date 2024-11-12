package br.ufes.willcq.sgp.api.dto;

import javax.validation.constraints.NotNull;

import br.ufes.willcq.sgp.model.Funcionario;

public class FuncionarioIdDTO {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Funcionario transforarParaFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(this.getId());
		return funcionario;
	}

}
