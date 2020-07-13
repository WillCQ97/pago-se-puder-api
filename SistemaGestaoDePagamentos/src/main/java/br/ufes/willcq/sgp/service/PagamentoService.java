package br.ufes.willcq.sgp.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.sgp.model.DetalhePagamento;
import br.ufes.willcq.sgp.model.Pagamento;

@Validated
public interface PagamentoService {
	
	public Iterable<Pagamento> listar();
	
	public Pagamento buscar(Long id);
	
	public Pagamento adicionar(Pagamento pagamento);
	
	public Pagamento atualizar(Long id, Pagamento pagamento);
	
	public void remover(Long id);
	
	public DetalhePagamento adicionarDetalhePagamento(Long idPagamento, DetalhePagamento detalhe);
}
