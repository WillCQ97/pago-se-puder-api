package br.ufes.willcq.sgp.api.dto;

import java.util.Date;

import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.ufes.willcq.sgp.model.DetalhePagamento;

public class DetalhePagamentoRespostaDTO {

	@Id
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;

	private String descricao;
	
	private DetalhePagamentoRespostaDTO(Long id, Date data, String descricao) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static DetalhePagamentoRespostaDTO paraDetalhePagamentoRespostaDTO(DetalhePagamento detalhe) {
		return new DetalhePagamentoRespostaDTO(detalhe.getId(), detalhe.getData(), detalhe.getDescricao());
	}
}
