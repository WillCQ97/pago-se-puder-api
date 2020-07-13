package br.ufes.willcq.sgp.api.dto;

import javax.validation.constraints.NotBlank;

import br.ufes.willcq.sgp.model.DetalhePagamento;

public class DetalhePagamentoDTO {

	@NotBlank(message = "A descrição do detalhe deve ser informada.")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public DetalhePagamento transformarParaDetalhePagamento() {
		return new DetalhePagamento(descricao);
	}

}
