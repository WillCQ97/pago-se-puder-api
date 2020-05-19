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

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
		
}
