package br.com.newfitness.model;

public enum PaymentType {

	MONEY("M"), CARD("C"), TICKET("T");

	private String statusCode;	
	
	private PaymentType(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}
}
