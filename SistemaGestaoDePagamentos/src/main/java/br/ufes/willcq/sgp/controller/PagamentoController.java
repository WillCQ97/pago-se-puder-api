package br.ufes.willcq.sgp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Pagamento> buscarPagamento(@PathVariable long id){
		return ResponseEntity.ok(pagamentoService.buscar(id));
	}
	
	//revisar
	@PostMapping
	public ResponseEntity<Pagamento> criarPagamento(@Valid @RequestBody Pagamento pagamento){
		
		Funcionario solicitante = funcionarioService.buscar(pagamento.getSolicitante().getId());
		pagamento.setSolicitante(solicitante);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(pagamentoService.adicionar(pagamento));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable long id, 
			@Valid @RequestBody Pagamento pagamento){
		
		Funcionario solicitante = funcionarioService.buscar(pagamento.getSolicitante().getId());
		pagamento.setSolicitante(solicitante);
		
		return ResponseEntity.ok(pagamentoService.atualizar(id, pagamento));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerPagamento(@PathVariable long id){
		pagamentoService.remover(id);
		return ResponseEntity.ok().build();
	}
	
	/*
	@PutMapping("/{id}/detalhes/")
	public ResponseEntity<Pagamento> adicionarDetalhePagamento(@PathVariable long id, 
			@Valid @RequestBody DetalhePagamento detalhe){
		
		Pagamento pagamento = pagamentoService.buscar(id);
		pagamento.addDetalhe(detalhe);
		
		return ResponseEntity.ok(pagamentoService.atualizar(id, pagamento));
	}
	*/
}
