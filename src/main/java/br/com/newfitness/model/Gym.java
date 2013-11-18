package br.com.newfitness.model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


/**
 * The persistent class for the academia database table.
 * 
 */
@Entity
@Table(name="gym")
public class Gym implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name="max_capacity")
	@NotNull(message="A capacidade maxima deve ser especificado")
	private Integer maxCapacity;

	@Column(name="opening")
	@NotNull(message="A hora de abertura deve ser especificado")
	@Range(min=00, max=24, message="A hora de abertura deve estar entre 00 e 24 horas")
	private Integer opening;

	@Column(name="closing")
	@NotNull(message="A hora de fechamento deve ser especificado")
	@Range(min=00, max=24, message="A hora de fechamento deve estar entre 00 e 24 horas")
	private Integer closing;
	
	@Column(name="amount")
	@NotNull(message="A capacidade maxima deve ser especificado")
	private BigDecimal amount;

	@Column(name="name")
	@NotEmpty(message="O Nome deve ser especificado")
	private String name;

	public Gym() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOpening() {
		return this.opening;
	}

	public void setOpening(Integer setOpening) {
		this.opening = setOpening;
	}

	public Integer getClosing() {
		return this.closing;
	}

	public void setClosing(Integer closing) {
		this.closing = closing;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	@Override
	public String toString() {
		return "Gym [id=" + id + ", maxCapacity=" + maxCapacity + ", opening="
				+ opening + ", closing=" + closing + ", nome=" + name + "]";
	}

}