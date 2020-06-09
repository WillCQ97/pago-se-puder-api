package br.ufes.willcq.sgp.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	//serialVersionUID on the original code is 5861310537366287163L

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(Throwable causa) {
		super(causa);
		// TODO Auto-generated constructor stub
	}
		
}
