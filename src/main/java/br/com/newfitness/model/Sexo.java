package br.com.newfitness.model;

public enum Sexo{

	M("Masculino"), F("Feminino");

	private String descricao;	
	
	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}
}
