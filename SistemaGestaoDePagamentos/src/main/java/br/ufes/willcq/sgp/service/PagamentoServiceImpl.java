package br.ufes.willcq.sgp.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.sgp.exception.NegocioException;
import br.ufes.willcq.sgp.exception.ResourceNotFoundException;
import br.ufes.willcq.sgp.model.DetalhePagamento;
import br.ufes.willcq.sgp.model.Pagamento;
import br.ufes.willcq.sgp.repository.DetalhePagamentoRepository;
import br.ufes.willcq.sgp.repository.PagamentoRepository;

@Service
@Transactional
public class PagamentoServiceImpl implements PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private DetalhePagamentoRepository detalhesPagamentoRepository;

	private void verificarPagamentoCadastrado(Long id) {
		if (!pagamentoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Pagamento de id " + id + " não encontrado.");
		}
	}

	private void validarValorPagamento(double valor) {
		if (valor == 0.0) {
			throw new NegocioException("O valor do pagamento deve ser maior que 0.00");
		}
	}

	private void verificarPagamentoAprovado(Long id) {
		Pagamento pagamento = pagamentoRepository.findById(id).get();
		if (pagamento.getAprovador() != null) {
			throw new NegocioException("Este pagamento já foi aprovado. Suas informações não podem ser alteradas.");
		}
	}

	@Override
	public Iterable<Pagamento> listar() {
		return pagamentoRepository.findAll();
	}

	@Override
	public Pagamento buscar(Long id) {
		this.verificarPagamentoCadastrado(id);
		return pagamentoRepository.findById(id).get();
	}
	
	@Override
	public Pagamento adicionar(Pagamento pagamento) {
		this.validarValorPagamento(pagamento.getValor());
		
		Pagamento pag = pagamentoRepository.save(pagamento);
		
		var detalhe = new DetalhePagamento(pag.getId(), new Date(), "Solicitação de pagamento criada.");
		detalhesPagamentoRepository.save(detalhe);
		pag.addDetalhe(detalhe);
		
		return pag;
	}

	@Override
	public Pagamento atualizar(Long id, Pagamento pagamento) {
		this.verificarPagamentoCadastrado(id);
		this.validarValorPagamento(pagamento.getValor());
		this.verificarPagamentoAprovado(id);
		
		var detalhe = new DetalhePagamento(id, new Date(), "Dados do pagamento foram atualizados.");
		detalhesPagamentoRepository.save(detalhe);
		
		pagamento.setId(id);
		pagamento.addDetalhe(detalhe);
		return pagamentoRepository.save(pagamento);
	}

	@Override
	public void remover(Long id) {
		this.verificarPagamentoCadastrado(id);
		this.verificarPagamentoAprovado(id);
		
		detalhesPagamentoRepository.deleteByCodigoPagamento(id);
		
		pagamentoRepository.deleteById(id);
	}
	
	
	public DetalhePagamento adicionarDetalhePagamento(Long idPagamento, DetalhePagamento detalhe) {
		this.verificarPagamentoCadastrado(idPagamento);
		this.verificarPagamentoAprovado(idPagamento);
		
		Pagamento pagamento = pagamentoRepository.findById(idPagamento).get();
		
		detalhe.setCodigoPagamento(idPagamento);
		detalhesPagamentoRepository.save(detalhe);
		pagamento.addDetalhe(detalhe);
		
		return detalhe;
	}

}
