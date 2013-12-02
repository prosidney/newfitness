package br.com.newfitness.model;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@NotEmpty(message="O endereço deve ser especificado")
	private String endereco;
	
	private String telefoneFixo;
	
	private String telefoneCelular;
	
	private String horarioTreinamento;
	
	@Range(min=10, max=100, message="Idade deve estar entre 10 e 100 anos de idade")
	private Integer idade;

	private BigDecimal peso;
	
	private Integer diaVencimentoParcela;
	
	private String observacao;
	
	@Transient
	private Status status;
	
	@Enumerated(EnumType.STRING)  
	private Genre sexo;
	
	//bi-directional many-to-one association to Exercicio
	@OneToMany(mappedBy="aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Payment> payments;

	private Date registerDate; 
	
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public Genre getSexo() {
		return sexo;
	}

	public void setSexo(Genre sexo) {
		this.sexo = sexo;
	}

	public List<Payment> getPayments() {
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
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {

		OK("Em dia"), NOK("Vencida");

		private String desc;	
		
		private Status(String desc) {
			this.desc = desc;
		}
		
		public String getSigla(){
			return this.name().toString();
		}

		public String toString() {
			return this.desc;
		}
	}
}