package br.ufes.willcq.sgp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.sgp.exception.ResourceNotFoundException;
import br.ufes.willcq.sgp.model.Funcionario;
import br.ufes.willcq.sgp.repository.FuncionarioRepository;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private void verificarFuncionarioCadastrado(Long id) {
		if(!funcionarioRepository.existsById(id)) {
			throw new ResourceNotFoundException("Funcionário com id " + id + " não encontrado!");
		}
	}
	
	@Override
	public Iterable<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@Override
	public Funcionario buscar(long id) {
		this.verificarFuncionarioCadastrado(id);
		return funcionarioRepository.findById(id).get();
	}

	@Override
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	/**
	 * A atualização deve receber os dados de todos os campos
	 */
	@Override
	public Funcionario atualizar(long id, Funcionario funcionario) {
		this.verificarFuncionarioCadastrado(id);
		funcionario.setIdFuncionario(id);
		
		return funcionarioRepository.save(funcionario);
	}
	
	@Override
	public void remover(long id) {
		this.verificarFuncionarioCadastrado(id);
		funcionarioRepository.deleteById(id);
	}
	
}
