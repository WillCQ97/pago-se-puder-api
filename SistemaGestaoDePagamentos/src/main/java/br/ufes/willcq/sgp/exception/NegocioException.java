package br.ufes.willcq.sgp.exception;

public class NegocioException extends RuntimeException{

	private static final long serialVersionUID = 1371748832025942639L;

	public NegocioException(String message) {
		super(message);
	}
	
}
