package br.ufes.willcq.sgp.controller;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.sgp.model.Funcionario;
import br.ufes.willcq.sgp.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
	
	private FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService funcionarioService) {
		super();
		this.funcionarioService = funcionarioService;
	}
	
	@GetMapping
	public @NotNull Iterable<Funcionario> getFuncionarios() {
		return funcionarioService.getAllFuncionarios();
	}
	
	@PostMapping
	public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioService.save(funcionario);
	}
	
}
