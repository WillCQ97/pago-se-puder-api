package br.ufes.willcq.sgp.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "funcionarios")
public class Funcionario implements Comparable<Funcionario>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionario;

	@NotNull(message = "O nome é necessário.")
	@Basic(optional = false)
	private String nome;
	
	@NotNull(message = "O cargo é requerido.")
	@Basic(optional = false)
	private String cargo;
	
	private int idade;
	
	private int qtdFaltas;
	
	public Funcionario() {
	}
	
	public Funcionario(@NotNull(message = "O nome é necessário.") String nome,
			@NotNull(message = "O cargo é requerido.") String cargo, int idade, int qtdFaltas) {
		super();
		this.nome = nome;
		this.cargo = cargo;
		this.idade = idade;
		this.qtdFaltas = qtdFaltas;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getQtdFaltas() {
		return qtdFaltas;
	}

	public void setQtdFaltas(int qtdFaltas) {
		this.qtdFaltas = qtdFaltas;
	}

	@Override
	public String toString() {
		return this.nome + "," + this.cargo + "," + this.idade + "," + this.qtdFaltas;
	}

	@Override
	public int compareTo(Funcionario f) {
		return this.nome.compareTo(f.getNome());
	}

}
