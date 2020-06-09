package br.ufes.willcq.sgp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.sgp.exception.ResourceNotFoundException;
import br.ufes.willcq.sgp.model.Pagamento;
import br.ufes.willcq.sgp.repository.PagamentoRepository;

@Service
@Transactional
public class PagamentoServiceImpl implements PagamentoService{
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Override
	public Iterable<Pagamento> getAllPagamentos() {
		return pagamentoRepository.findAll();
	}

	@Override
	public Pagamento getPagamento(long id) {
		return pagamentoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pagamento de id " + id + " não encontrado."));
	}

	@Override
	public Pagamento salvar(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}
	
	/* fix-me */
	@Override
	public Pagamento atualizar(long id, Pagamento pagamento) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* fix-me */
	@Override
	public String remover(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
