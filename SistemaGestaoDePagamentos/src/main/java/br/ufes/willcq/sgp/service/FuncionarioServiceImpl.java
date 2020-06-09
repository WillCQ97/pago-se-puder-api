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

	@Override
	public Iterable<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@Override
	public Funcionario getFuncionario(long id) {
		return funcionarioRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionário com id" + id + "não encontrado!"));
	}

	@Override
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	/**
	 * A atualização deve receber os dados de todos os campos
	 */
	@Override
	public Funcionario atualizar(long id, Funcionario detalhesFuncionario) {
		Funcionario funcionario = this.getFuncionario(id);
		
		funcionario.setNome(detalhesFuncionario.getNome());
		funcionario.setCargo(detalhesFuncionario.getCargo());
		funcionario.setQtdFaltas(detalhesFuncionario.getQtdFaltas());
		funcionario.setIdade(detalhesFuncionario.getIdade());
		return funcionario;
	}
	
	@Override
	public String remover(long id) {
		Funcionario funcionario = this.getFuncionario(id);
		funcionarioRepository.delete(funcionario);
		return "Dados do funcionário de id " + id + " foram removidos.";
	}
	
}
