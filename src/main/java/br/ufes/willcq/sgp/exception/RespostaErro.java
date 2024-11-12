package br.ufes.willcq.sgp.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class RespostaErro {

	private Integer status;
	private OffsetDateTime dataHora;
	private String mensagem;
	private List<ItemErro> erros;

	public RespostaErro() {
		erros = new ArrayList<>();
	}

	public RespostaErro(Integer status, OffsetDateTime dataHora, String mensagem) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.mensagem = mensagem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setErros(List<ItemErro> erros) {
		this.erros = erros;
	}

	public List<ItemErro> getErros() {
		return erros;
	}

	public void setErrors(List<ItemErro> erros) {
		this.erros = erros;
	}

	public void addError(ItemErro erro) {
		this.erros.add(erro);
	}

}
