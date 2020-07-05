package br.ufes.willcq.sgp.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.sgp.model.Pagamento;
import br.ufes.willcq.sgp.service.PagamentoService;

@RestController
@RequestMapping("/api/v0/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping
	public @NotNull Iterable<Pagamento> getPagamentos(){
		return pagamentoService.getAllPagamentos();
	}
}
