package br.ufes.willcq.sgp.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.ufes.willcq.sgp.model.Funcionario;

public class FuncionarioPagamentoDTO {
	
	@NotNull
	private Long id;
	
	@NotBlank
	private String nome;
	
	private FuncionarioPagamentoDTO(@NotNull Long id, @NotBlank String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public static FuncionarioPagamentoDTO paraFuncionarioPagamentoDTO(Funcionario funcionario) {
		return new FuncionarioPagamentoDTO(funcionario.getId(), funcionario.getNome());
	}
}
