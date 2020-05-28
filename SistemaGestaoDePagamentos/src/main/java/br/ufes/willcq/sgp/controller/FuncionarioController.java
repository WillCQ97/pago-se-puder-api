package br.ufes.willcq.sgp.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public @NotNull Iterable<Funcionario> getFuncionarios() {
		return funcionarioService.getAllFuncionarios();
	}
	
	@GetMapping("/{id}")
	public Funcionario getFuncionario(@PathVariable(value = "id") long id) {
		return funcionarioService.getFuncionario(id);
	}
	
	@PostMapping
	public Funcionario criarFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioService.salvar(funcionario);
	}
	
	@PutMapping("/{id}")
	public Funcionario atualizarFuncionario(@PathVariable(value = "id") long id, @RequestBody Funcionario funcionario) {
		return funcionarioService.atualizar(id, funcionario);
	}
	
	@DeleteMapping("/{id}")
	public Funcionario removerFuncionario(@PathVariable(value = "id") long id) {
		return funcionarioService.remover(id);
	}
	
}
