package br.com.newfitness.model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the aluno database table.
 * 
 */
@Entity
@Table(name="aluno")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=AUTO)
	private Integer matricula;

	@NotEmpty(message="O Nome deve ser especificado")
	private String nome;
	
	@NotEmpty(message="O CPF deve ser especificado")
	private String cpf;
	
	@NotEmpty(message="O RG deve ser especificado")
	private String rg;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataNasc;
	
	@NotEmpty(message="O bairro deve ser especificado")
	private String Bairro;
	
	@NotEmpty(message="A rua deve ser especificado")
	private String Rua;
	
	private Integer numero;
	
	private String telefoneFixo;
	
	private String telefoneCelular;
	
	private String horarioTreinamento;
	
	@Range(min=10, max=100, message="Idade deve estar entre 10 e 100 anos de idade")
	private Integer idade;

	private BigDecimal peso;
	
	private Integer diaVencimentoParcela;
	
	private String observacao;
	
	@Enumerated(EnumType.STRING)  
	private Sexo sexo;
	
	//bi-directional many-to-one association to Exercicio
	@OneToMany(mappedBy="aluno", fetch = FetchType.EAGER)
	private List<Payment> payments;

	private Date registerDate; 
	
	private Status status;

	public Aluno() {
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getRua() {
		return Rua;
	}

	public void setRua(String rua) {
		Rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getHorarioTreinamento() {
		return horarioTreinamento;
	}

	public void setHorarioTreinamento(String horarioTreinamento) {
		this.horarioTreinamento = horarioTreinamento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getDiaVencimentoParcela() {
		return diaVencimentoParcela;
	}

	public void setDiaVencimentoParcela(Integer diaVencimentoParcela) {
		this.diaVencimentoParcela = diaVencimentoParcela;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public List<Payment> getPayments() {
		if(payments != null){
			Collections.sort(payments);
		}
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Status getStatus() {
		
		if(status == null){
			GregorianCalendar today = new GregorianCalendar();
			today.setTime(new Date());
			today.set(Calendar.HOUR_OF_DAY,0);
			today.set(Calendar.MINUTE,0);
			today.set(Calendar.SECOND,0);
			today.set(Calendar.MILLISECOND,0);
			
			List<Payment> payments = this.getPayments();
			
			if(payments.size() > 0){
				Payment lastPayment = payments.get(0);
				
				GregorianCalendar lastExpirationDateGC = new GregorianCalendar();
				lastExpirationDateGC.setTime(lastPayment.getExpirationDate());
				
				lastExpirationDateGC.add(Calendar.MONTH, 1);
				
				if(lastExpirationDateGC.after(today) || lastExpirationDateGC.equals(today)){
					this.setStatus(Status.ED);
				} else {
					this.setStatus(Status.VA);
				}
			} else {
				GregorianCalendar registerDt = new GregorianCalendar();
				registerDt.setTime(registerDate);

				registerDt.add(Calendar.MONTH, 1);
				
				if(registerDt.after(today) || registerDt.equals(today)){
					this.setStatus(Status.ED);
				} else {
					this.setStatus(Status.VA);
				}
			}
		}
		
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {

		VA("Vencida"), ED("Em dia");

		private String desc;	
		
		private Status(String descricao) {
			this.desc = descricao;
		}
		
		public String getSigla(){
			return this.name().toString();
		}

		public String toString() {
			return this.desc;
		}
	}
}