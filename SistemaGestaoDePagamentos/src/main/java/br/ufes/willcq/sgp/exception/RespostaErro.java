package br.ufes.willcq.sgp.exception;

import java.util.ArrayList;
import java.util.List;

public class RespostaErro {

	private List<ItemErro> erros;
	
	public RespostaErro() {
		erros = new ArrayList<>();
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
