package br.ufes.willcq.sgp.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.sgp.model.Funcionario;

@Validated
public interface FuncionarioService {
	
	public Iterable<Funcionario> listar();
	
	public Funcionario buscar(long id);
	
	public Funcionario salvar(Funcionario funcionario);
	
	public Funcionario atualizar(long id, Funcionario funcionario);
	
	public void remover(long id);
	
}
