package br.ufes.willcq.sgp.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.ufes.willcq.sgp.model.DetalhePagamento;
import br.ufes.willcq.sgp.model.Pagamento;

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

	private List<DetalhePagamentoRespostaDTO> detalhes;

	private PagamentoRespostaDTO(Long id, String descricao, double valor, Date dataVencimento, Date dataAprovacao) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataAprovacao = dataAprovacao;
	}

	public long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public FuncionarioPagamentoDTO getSolicitante() {
		return solicitante;
	}

	private void setSolicitante(FuncionarioPagamentoDTO funcionario) {
		this.solicitante = funcionario;
	}

	public FuncionarioPagamentoDTO getAprovador() {
		return aprovador;
	}

	private void setAprovador(FuncionarioPagamentoDTO funcionario) {
		this.solicitante = funcionario;
	}

	public List<DetalhePagamentoRespostaDTO> getDetalhes() {
		return detalhes;
	}

	private void addDetalhe(DetalhePagamentoRespostaDTO detalhe) {
		if (this.detalhes == null) {
			this.detalhes = new ArrayList<>();
		}
		this.detalhes.add(detalhe);
	}

	public static PagamentoRespostaDTO paraPagamentoRespostaDTO(Pagamento pagamento) {
		PagamentoRespostaDTO resposta = new PagamentoRespostaDTO(pagamento.getId(), pagamento.getDescricao(),
				pagamento.getValor(), pagamento.getDataVencimento(), pagamento.getDataAprovacao());

		// converter os funcionarios
		resposta.setSolicitante(FuncionarioPagamentoDTO.paraFuncionarioPagamentoDTO(pagamento.getSolicitante()));
		if (pagamento.getAprovador() != null) {
			resposta.setAprovador(FuncionarioPagamentoDTO.paraFuncionarioPagamentoDTO(pagamento.getAprovador()));
		}
		
		// converter os detalhes
		for (DetalhePagamento detalhe : pagamento.getDetalhes()) {
			resposta.addDetalhe(DetalhePagamentoRespostaDTO.paraDetalhePagamentoRespostaDTO(detalhe));
		}

		return resposta;
	}
}
