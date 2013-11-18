package br.com.newfitness.model;

public enum Genre{

	M("Masculino"), F("Feminino");

	private String desc;	
	
	private Genre(String desc) {
		this.desc = desc;
	}

	public String toString() {
		return this.desc;
	}
}
