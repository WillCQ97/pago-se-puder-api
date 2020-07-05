package br.ufes.willcq.sgp.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RespostaErro {

	private Integer status;
	private LocalDateTime dataHora;
	private String mensagem;
	private List<ItemErro> erros;

	public RespostaErro() {
		erros = new ArrayList<>();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
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
