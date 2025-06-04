package br.com.xavecoding.regescweb.dto;
import java.math.BigDecimal;

import br.com.xavecoding.models.Professor;
import br.com.xavecoding.models.StatusProfessor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Classe DTO: Data Transfer Object
public class RequisicaoFormProfessor {
	

	@NotBlank(message = "O Nome do Professor não pode estar vazio!")
    @NotNull(message = "O Nome do Professor não pode ser nulo!")
	private String nome;

	@NotNull(message = "O Salário do Professor não pode ser vazio!")
	@DecimalMin("0.0")
	private BigDecimal salario;
	private StatusProfessor statusProfessor;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public StatusProfessor getStatusProfessor() {
		return statusProfessor;
	}
	public void setStatusProfessor(StatusProfessor statusProfessor) {
		this.statusProfessor = statusProfessor;
	}
	
	public Professor toProfessor() {
		Professor professor = new Professor();
		professor.setNome(this.nome.toUpperCase());
		professor.setSalario(this.salario);
		professor.setStatusProfessor(this.statusProfessor);
		
		return professor;
	}
	
	public void fromProfessor(Professor professor) {
		this.nome = professor.getNome();
		this.salario = professor.getSalario();
		this.statusProfessor = professor.getStatusProfessor();
	}
	
	public Professor toProfessor(Professor professor) {
		professor.setNome(this.nome);
		professor.setSalario(this.salario);
		professor.setStatusProfessor(this.statusProfessor);
		
		return professor;
	}
	@Override
	public String toString() {
		return "RequisicaoNovoProfessor [nome=" + nome + ", salario=" + salario + ", statusProfessor=" + statusProfessor
				+ "]";
	}
	
	
	
	
	
	
	

}
