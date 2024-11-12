package br.ufes.willcq.sgp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ItemErro {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String campo;
	private String mensagem;

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
