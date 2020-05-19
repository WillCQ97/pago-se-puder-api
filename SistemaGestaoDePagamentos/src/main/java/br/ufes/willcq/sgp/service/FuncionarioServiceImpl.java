package br.ufes.willcq.sgp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.sgp.exception.ResourceNotFoundException;
import br.ufes.willcq.sgp.model.Funcionario;
import br.ufes.willcq.sgp.repository.FuncionarioRepository;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {
	
	private FuncionarioRepository funcionarioRepository;

	public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
		super();
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	public Iterable<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@Override
	public Funcionario getFuncionario(long id) {
		return funcionarioRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Funcionário não encontrado."));
	}

	@Override
	public Funcionario save(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
		
}
