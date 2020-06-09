package br.ufes.willcq.sgp.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.sgp.model.Pagamento;

@Validated
public interface PagamentoService {
	
	public Iterable<Pagamento> getAllPagamentos();
	
	public Pagamento getPagamento(long id);
	
	public Pagamento salvar(Pagamento pagamento);
	
	public Pagamento atualizar(long id, Pagamento pagamento);
	
	public String remover(long id);
}
