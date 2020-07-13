package br.ufes.willcq.sgp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufes.willcq.sgp.model.DetalhePagamento;

public interface DetalhePagamentoRepository extends CrudRepository<DetalhePagamento, Long>{
	
	List<DetalhePagamento> findByCodigoPagamento(Long codigoPagamento);
	
	void deleteByCodigoPagamento(Long codigoPagamento);
	
}
