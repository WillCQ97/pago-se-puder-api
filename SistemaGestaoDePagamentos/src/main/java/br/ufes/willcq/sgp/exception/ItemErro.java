package br.ufes.willcq.sgp.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ItemErro {
	
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String codigo;
	private String mensagem;
	private LocalDateTime dataHora;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
