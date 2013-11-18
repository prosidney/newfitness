package br.com.newfitness.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8926861459590112658L;

	public BusinessException(String exc) {
		super(exc);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
