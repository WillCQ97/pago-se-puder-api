package br.ufes.willcq.sgp.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "funcionarios")
public class Funcionario implements Comparable<Funcionario>, Serializable {

	private static final long serialVersionUID = -762959886428920284L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcionario")
	private long id;

	@NotBlank(message = "O nome é necessário.")
	@Basic(optional = false)
	@Size(max = 250)
	private String nome;

	@NotBlank(message = "O cargo é requerido.")
	@Basic(optional = false)
	@Size(max = 250)
	private String cargo;

	private int idade;

	@Column(name = "qtd_faltas")
	private int quantidadeFaltas;

	public Funcionario() {
	}

	public Funcionario(String nome, String cargo, int idade, int qtdFaltas) {
		super();
		this.nome = nome;
		this.cargo = cargo;
		this.idade = idade;
		this.quantidadeFaltas = qtdFaltas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getQuantidadeFaltas() {
		return quantidadeFaltas;
	}

	public void setQuantidadeFaltas(int quantidadeFaltas) {
		this.quantidadeFaltas = quantidadeFaltas;
	}

	@Override
	public String toString() {
		return this.nome + "," + this.cargo + "," + this.idade + "," + this.quantidadeFaltas;
	}

	@Override
	public int compareTo(Funcionario f) {
		return this.nome.compareTo(f.getNome());
	}

}
