package br.com.newfitness.model;

public enum PaymentType {

	DI("Dinheiro"), CA("Cart�o"), CH("Cheque");

	private String desc;	
	
	private PaymentType(String descricao) {
		this.desc = descricao;
	}

	public String toString() {
		return this.desc;
	}
}
