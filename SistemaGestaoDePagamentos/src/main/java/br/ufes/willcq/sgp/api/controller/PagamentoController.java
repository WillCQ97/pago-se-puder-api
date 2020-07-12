package br.ufes.willcq.sgp.api.controller;

import java.util.ArrayList;
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

import br.ufes.willcq.sgp.api.dto.PagamentoDTO;
import br.ufes.willcq.sgp.api.dto.PagamentoRespostaDTO;
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

	private Iterable<PagamentoRespostaDTO> paraCollectionPagamentoRespostaDTO(Iterable<Pagamento> pagamentos){
		ArrayList<PagamentoRespostaDTO> pagamentosDTO = new ArrayList<>();
		
		for(Pagamento pagamento : pagamentos) {
			pagamentosDTO.add(this.paraPagamentoRespostaDTO(pagamento));
		}
		return pagamentosDTO;
	}
	
	private PagamentoRespostaDTO paraPagamentoRespostaDTO(Pagamento pagamento) {
		return PagamentoRespostaDTO.paraPagamentoRespostaDTO(pagamento);
	}

	@GetMapping
	public Iterable<PagamentoRespostaDTO> listarPagamentos() {
		return this.paraCollectionPagamentoRespostaDTO(pagamentoService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PagamentoRespostaDTO> buscarPagamento(@PathVariable long id) {
		Pagamento pagamento = pagamentoService.buscar(id);
		return ResponseEntity.ok(this.paraPagamentoRespostaDTO(pagamento));
	}

	@PostMapping
	public ResponseEntity<PagamentoRespostaDTO> criarPagamento(@Valid @RequestBody PagamentoDTO pagamentoDto) {
		
		Pagamento pagamento = pagamentoDto.transformarParaPagamento();
		Funcionario solicitante = funcionarioService.buscar(pagamento.getSolicitante().getId());
		pagamento.setSolicitante(solicitante);
		
		PagamentoRespostaDTO resposta = this.paraPagamentoRespostaDTO(pagamentoService.adicionar(pagamento));
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PagamentoRespostaDTO> atualizarPagamento(@PathVariable long id,
			@Valid @RequestBody PagamentoDTO pagamentoDto) {
		
		Pagamento pagamento = pagamentoDto.transformarParaPagamento();
		Funcionario solicitante = funcionarioService.buscar(pagamento.getSolicitante().getId());
		pagamento.setSolicitante(solicitante);
		
		PagamentoRespostaDTO resposta = this.paraPagamentoRespostaDTO(pagamentoService.atualizar(id, pagamento));
		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerPagamento(@PathVariable long id) {
		pagamentoService.remover(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/*
	 * @PutMapping("/{id}/detalhes/") public ResponseEntity<Pagamento>
	 * adicionarDetalhePagamento(@PathVariable long id,
	 * 
	 * @Valid @RequestBody DetalhePagamento detalhe){
	 * 
	 * Pagamento pagamento = pagamentoService.buscar(id);
	 * pagamento.addDetalhe(detalhe);
	 * 
	 * return ResponseEntity.ok(pagamentoService.atualizar(id, pagamento)); }
	 */
}
