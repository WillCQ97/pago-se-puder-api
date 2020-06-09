package br.ufes.willcq.sgp.controller;

import java.util.Map;

import javax.validation.constraints.NotNull;

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
import br.ufes.willcq.sgp.service.FuncionarioService;

@RestController
@RequestMapping("/api/v0/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public @NotNull ResponseEntity<Iterable<Funcionario>> getFuncionarios() {
		return ResponseEntity.ok(funcionarioService.getAllFuncionarios());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> getFuncionario(@PathVariable(value = "id") long id) {
		return ResponseEntity.ok().body(funcionarioService.getFuncionario(id));
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> criarFuncionario(@RequestBody Funcionario funcionario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.salvar(funcionario));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable(value = "id") long id, 
			@RequestBody Funcionario funcionario) {
		return ResponseEntity.ok(funcionarioService.atualizar(id, funcionario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> removerFuncionario(@PathVariable(value = "id") long id) {
		return ResponseEntity.ok(funcionarioService.remover(id));
	}
	
}
