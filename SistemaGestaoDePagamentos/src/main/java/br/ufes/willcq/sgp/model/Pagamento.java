package br.ufes.willcq.sgp.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/* Supostamente deverá ser criado uma classe PagamentoServiceImpl, e interfaces para 
 * PagamentoService e PagamentoRepository, como foi feito para Produto */

@Entity
@Table(name = "pagamentos")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPagamento; //alterar tipo pra long
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "A data de vencimento é necesária.")
	@Basic(optional = false)
	private Date dataVencimento;
	
	@Basic(optional = false)
	@NotNull(message = "A descricao é necessária.")
	private String descricao;
	
	@Basic(optional = false)
	@NotNull(message = "O valor é necessário.")
	private double valor;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAprovacao;
	/*
	@NotNull(message = "O funcionário solicitante é necessário.")
	private Funcionario solicitante;
	
	private Funcionario aprovador;
	*/
	
	private int idSolicitante;
	
	//private int idAprovador; //valor nulo, quando retornado do banco da erro
	//talvez utilizar integer, pois neste caso ele poderá ser null
	/*
	@JsonManagedReference
	@OneToMany(mappedBy = "codigoPagamento")
	@Valid
	private ArrayList<DetalhePagamento> detalhes;
	*/
	public Pagamento() {
	}
	/*
	public Pagamento(Date dataVencimento, String descricao, double valor, Funcionario solicitante) {
		super();
		this.dataVencimento = dataVencimento;
		this.descricao = descricao;
		this.valor = valor;
		//this.solicitante = solicitante;
	}
	*/
	// contrutor completo (todos os detalhes)
	
	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public int getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
	}
	/*
	public int getIdAprovador() {
		return idAprovador;
	}

	public void setIdAprovador(int idAprovador) {
		this.idAprovador = idAprovador;
	}
	*/
	/*
	public Funcionario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Funcionario solicitante) {
		this.solicitante = solicitante;
	}

	public Funcionario getAprovador() {
		return aprovador;
	}

	public void setAprovador(Funcionario aprovador) {
		this.aprovador = aprovador;
	}
	*/
	/*
	public ArrayList<DetalhePagamento> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(ArrayList<DetalhePagamento> detalhes) {
		this.detalhes = detalhes;
	}
	
	public void addDetalhe(Date data, String descricao, String usuario) {
		detalhes.add(new DetalhePagamento(this.codigo, data, descricao, usuario));
	}
	*/
	/*
	@Override
	public String toString() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		String pagamento = codigo + "," + formatoData.format(dataVencimento) + "," + descricao + "," + valor + ",";
		
		if(this.dataAprovacao != null) {
			pagamento += formatoData.format(dataAprovacao);
		}else {
			pagamento += null;
		}
		
		pagamento += "," + solicitante.getNome() + "," + solicitante.getCargo();
		
		if(this.aprovador != null) {
			pagamento += aprovador.getNome() + "," + aprovador.getCargo();
		}else {
			pagamento += "null, null";
		}
		
		return pagamento;
	}
	*/
	
}
