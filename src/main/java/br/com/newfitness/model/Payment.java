package br.com.newfitness.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="payment")
public class Payment implements Serializable, Comparable<Payment> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message="O Valor deve ser especificado")
	private BigDecimal amount;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull(message="A data de vencimento ser especificada")
	private Date expirationDate;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dtPayment;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="O Tipo de pagamento deve ser especificado")
	private PaymentType paymentType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ALUNO_ID")
	private Aluno aluno;
	
	private String observation;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getDtPayment() {
		return dtPayment;
	}

	public void setDtPayment(Date dtPayment) {
		this.dtPayment = dtPayment;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public int compareTo(Payment other) {
		if(other.getExpirationDate() != null){
			return other.getExpirationDate().compareTo(expirationDate);
		} else{
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Payment [amount=" + amount + ", expirationDate="
				+ expirationDate + ", dtPayment=" + dtPayment
				+ ", paymentType=" + paymentType + ", aluno=" + aluno
				+ ", observation=" + observation + "]";
	}
}
