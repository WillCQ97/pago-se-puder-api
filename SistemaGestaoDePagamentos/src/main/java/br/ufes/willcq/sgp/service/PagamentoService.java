package br.ufes.willcq.sgp.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.sgp.model.Pagamento;

@Validated
public interface PagamentoService {
	
	public Iterable<Pagamento> listar();
	
	public Pagamento buscar(long id);
	
	public Pagamento adicionar(Pagamento pagamento);
	
	public Pagamento atualizar(long id, Pagamento pagamento);
	
	public void remover(long id);
}
