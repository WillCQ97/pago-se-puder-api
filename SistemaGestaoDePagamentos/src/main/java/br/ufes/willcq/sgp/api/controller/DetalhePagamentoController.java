package br.ufes.willcq.sgp.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.sgp.api.dto.DetalhePagamentoDTO;
import br.ufes.willcq.sgp.api.dto.DetalhePagamentoRespostaDTO;
import br.ufes.willcq.sgp.model.DetalhePagamento;
import br.ufes.willcq.sgp.service.PagamentoService;

@RestController
@RequestMapping("/api/v0/pagamentos/{idPagamento}/detalhes")
public class DetalhePagamentoController {

	@Autowired
	private PagamentoService pagamentoService;

	@GetMapping
	public Iterable<DetalhePagamentoRespostaDTO> listarDetalhes(@PathVariable Long idPagamento) {
		List<DetalhePagamentoRespostaDTO> resposta = new ArrayList<>();

		for (DetalhePagamento detalhe : pagamentoService.buscar(idPagamento).getDetalhes()) {
			resposta.add(DetalhePagamentoRespostaDTO.paraDetalhePagamentoRespostaDTO(detalhe));
		}

		return resposta;
	}

	@PostMapping
	public ResponseEntity<DetalhePagamentoRespostaDTO> adicionarDetalhePagamento(@PathVariable Long idPagamento,
			@Valid @RequestBody DetalhePagamentoDTO detalheDto) {

		DetalhePagamento detalhe = detalheDto.transformarParaDetalhePagamento();
		detalhe.setData(new Date());

		DetalhePagamento salvo = pagamentoService.adicionarDetalhePagamento(idPagamento, detalhe);

		return ResponseEntity.ok(DetalhePagamentoRespostaDTO.paraDetalhePagamentoRespostaDTO(salvo));
	}

}
