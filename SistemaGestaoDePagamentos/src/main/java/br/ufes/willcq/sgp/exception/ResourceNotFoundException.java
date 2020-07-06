package br.ufes.willcq.sgp.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2071458723696140003L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}

	public ResourceNotFoundException(Throwable causa) {
		super(causa);
	}

}
