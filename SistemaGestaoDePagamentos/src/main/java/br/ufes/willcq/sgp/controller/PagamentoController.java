package br.ufes.willcq.sgp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.sgp.model.Funcionario;
import br.ufes.willcq.sgp.model.Pagamento;
import br.ufes.willcq.sgp.service.FuncionarioService;
import br.ufes.willcq.sgp.service.PagamentoService;

@RestController
@RequestMapping("/api/v0/pagamentos")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public Iterable<Pagamento> listarPagamentos(){
		return pagamentoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pagamento> buscarPagamento(@PathVariable Long id){
		return ResponseEntity.ok(pagamentoService.buscar(id));
	}
	
	@PostMapping
	public ResponseEntity<Pagamento> criarPagamento(@Valid @RequestBody Pagamento pagamento){
		Funcionario solicitante = funcionarioService.buscar(pagamento.getSolicitante().getId());
		
		pagamento.setSolicitante(solicitante);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(pagamentoService.salvar(pagamento));
	}
}
