package br.ufes.willcq.sgp.api.controller;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.ufes.willcq.sgp.model.Funcionario;
import br.ufes.willcq.sgp.service.FuncionarioService;

@RestController
@RequestMapping("/api/v0/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public @NotNull Iterable<Funcionario> listarTodosFuncionarios() {
		return funcionarioService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable long id) {
		return ResponseEntity
				.ok()
				.body(funcionarioService.buscar(id));
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> criarFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(funcionarioService.adicionar(funcionario));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable long id, 
			@Valid @RequestBody Funcionario funcionario) {
		return ResponseEntity.ok(funcionarioService.atualizar(id, funcionario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerFuncionario(@PathVariable long id) {
		funcionarioService.remover(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	//fix-me: melhorar implementação
	@PostMapping("/importacao")
	public Iterable<Funcionario> importarDadosFuncionarioDeArquivo(@RequestParam MultipartFile arquivo) {
		return funcionarioService.importar(arquivo);
	}
}
