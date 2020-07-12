package br.ufes.willcq.sgp.api.controller;

import java.util.ArrayList;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.ufes.willcq.sgp.api.dto.PagamentoInputModel;
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

	@Autowired
	private ModelMapper modelMapper;

	private Iterable<PagamentoRespostaDTO> toCollectionPagamentoOutputModel(Iterable<Pagamento> pagamentos){
		ArrayList<PagamentoRespostaDTO> pagamentosModel = new ArrayList<>();
		
		for(Pagamento pagamento : pagamentos) {
			pagamentosModel.add(this.toPagamentoOutputModel(pagamento));
		}
		return pagamentosModel;
	}

	private PagamentoRespostaDTO toPagamentoOutputModel(Pagamento pagamento) {
		return modelMapper.map(pagamento, PagamentoRespostaDTO.class);
	}
	
	private Pagamento toPagamento(PagamentoInputModel input) {
		return modelMapper.map(input, Pagamento.class);
	}

	@GetMapping
	public Iterable<PagamentoRespostaDTO> listarPagamentos() {
		return this.toCollectionPagamentoOutputModel(pagamentoService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PagamentoRespostaDTO> buscarPagamento(@PathVariable long id) {
		Pagamento pagamento = pagamentoService.buscar(id);
		return ResponseEntity.ok(this.toPagamentoOutputModel(pagamento));
	}

	@PostMapping
	public ResponseEntity<PagamentoRespostaDTO> criarPagamento(@Valid @RequestBody PagamentoInputModel input) {
		
		Pagamento pagamento = this.toPagamento(input);
		Funcionario solicitante = funcionarioService.buscar(pagamento.getSolicitante().getId());
		pagamento.setSolicitante(solicitante);
		
		PagamentoRespostaDTO output = this.toPagamentoOutputModel(pagamentoService.adicionar(pagamento));
		return ResponseEntity.status(HttpStatus.CREATED).body(output);
	}

	@PutMapping("/{id}") //pensar em uma solução aqui
	public ResponseEntity<PagamentoRespostaDTO> atualizarPagamento(@PathVariable long id,
			@Valid @RequestBody Pagamento pagamento) {

		Funcionario solicitante = funcionarioService.buscar(pagamento.getSolicitante().getId());
		pagamento.setSolicitante(solicitante);
		
		PagamentoRespostaDTO output = this.toPagamentoOutputModel(pagamentoService.atualizar(id, pagamento));
		return ResponseEntity.ok(output);
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
