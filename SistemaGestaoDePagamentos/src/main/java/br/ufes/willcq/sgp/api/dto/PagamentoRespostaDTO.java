package br.ufes.willcq.sgp.api.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.ufes.willcq.sgp.model.Pagamento;

//utilizar anotações para substituir código
@JsonInclude(Include.NON_EMPTY)
public class PagamentoRespostaDTO {

	private Long id;
	private String descricao;
	private double valor;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataAprovacao;

	private FuncionarioPagamentoDTO solicitante;
	private FuncionarioPagamentoDTO aprovador;

	private List<DetalhePagamentoDTO> detalhes;
	
	public PagamentoRespostaDTO() {
	}

	public PagamentoRespostaDTO(Long id, String descricao, double valor, Date dataVencimento, Date dataAprovacao,
			FuncionarioPagamentoDTO solicitante, FuncionarioPagamentoDTO aprovador,
			List<DetalhePagamentoDTO> detalhes) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataAprovacao = dataAprovacao;
		this.solicitante = solicitante;
		this.aprovador = aprovador;
		this.detalhes = detalhes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public FuncionarioPagamentoDTO getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(FuncionarioPagamentoDTO solicitante) {
		this.solicitante = solicitante;
	}

	public FuncionarioPagamentoDTO getAprovador() {
		return aprovador;
	}

	public void setAprovador(FuncionarioPagamentoDTO aprovador) {
		this.aprovador = aprovador;
	}

	public List<DetalhePagamentoDTO> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<DetalhePagamentoDTO> detalhes) {
		this.detalhes = detalhes;
	}
	
	//método que retorna um PagamentoRespostaDTO ao receber um objeto de pagamento
	public PagamentoRespostaDTO paraPagamentoRespostaDTO(Pagamento pagamento) {
		return new PagamentoRespostaDTO();
	}
}
