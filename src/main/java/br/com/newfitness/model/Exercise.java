package br.com.newfitness.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the exercicio database table.
 * 
 */
@Entity
@Table(name="exercise")
public class Exercise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String equipamento;

	private String execucao;

	private String musculatura;

	private String qualidade;

	//bi-directional many-to-one association to Treino
	@ManyToOne
	@JoinColumn(name="TREINO_ID")
	private Training training;

	public Exercise() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEquipamento() {
		return this.equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getExecucao() {
		return this.execucao;
	}

	public void setExecucao(String execucao) {
		this.execucao = execucao;
	}

	public String getMusculatura() {
		return this.musculatura;
	}

	public void setMusculatura(String musculatura) {
		this.musculatura = musculatura;
	}

	public String getQualidade() {
		return this.qualidade;
	}

	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public Training getTraining() {
		return this.training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	@Override
	public String toString() {
		return "Exercicio [id=" + id + ", equipamento=" + equipamento
				+ ", execucao=" + execucao + ", musculatura=" + musculatura
				+ ", qualidade=" + qualidade + "]";
	}
}