package br.ufes.willcq.sgp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ItemErro {
	
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String codigo;
	private String mensagem;

	
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
	
}
