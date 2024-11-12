package br.ufes.willcq.sgp.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.ufes.willcq.sgp.model.Pagamento;

public class PagamentoDTO {

	@NotBlank(message = "A descrição deve ser informada.")
	private String descricao;

	@NotNull(message = "O valor deve ser informado.")
	private double valor;

	@NotNull(message = "A dada de vencimento deve ser informada.")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@NotNull(message = "O código do funcionário solicitante deve ser informado.")
	private FuncionarioIdDTO solicitante;

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

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public FuncionarioIdDTO getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(FuncionarioIdDTO solicitante) {
		this.solicitante = solicitante;
	}
	
	public Pagamento transformarParaPagamento() {
		return new Pagamento(this.dataVencimento, this.descricao, this.valor, 
				solicitante.transforarParaFuncionario());
	}
}
