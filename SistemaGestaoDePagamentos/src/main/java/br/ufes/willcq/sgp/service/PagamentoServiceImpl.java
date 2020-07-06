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
	
	private void verificarPagamentoCadastrado(Long id) {
		if(!pagamentoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Pagamento de id " + id + " não encontrado.");
		}
	}
	
	@Override
	public Iterable<Pagamento> listar() {
		return pagamentoRepository.findAll();
	}

	@Override
	public Pagamento buscar(long id) {
		this.verificarPagamentoCadastrado(id);
		return pagamentoRepository.findById(id).get();
	}

	/* fix-me : verificar se é necessário fazer validações */
	@Override
	public Pagamento salvar(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}
	
	/* fix-me : verificar se é necessário fazer validações */
	@Override
	public Pagamento atualizar(long id, Pagamento pagamento) {
		this.verificarPagamentoCadastrado(id);
		
		pagamento.setId(id);
		return pagamentoRepository.save(pagamento);
	}
	
	/* fix-me : verificar se é necessário fazer validações */
	@Override
	public void remover(long id) {
		this.verificarPagamentoCadastrado(id);
		
		pagamentoRepository.deleteById(id);
	}

}
