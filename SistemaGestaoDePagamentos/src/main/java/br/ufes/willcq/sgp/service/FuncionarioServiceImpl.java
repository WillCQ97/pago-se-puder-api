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
		return funcionarioRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado."));
	}

	@Override
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	/**
	 * corrija-me por favor 
	 */
	@Override
	public Funcionario atualizar(long id, Funcionario funcionario) {
		Funcionario f = funcionarioRepository.findById(id).get();
		f.setNome(funcionario.getNome());
		f.setCargo(funcionario.getCargo());
		f.setQtdFaltas(funcionario.getQtdFaltas());
		f.setIdade(funcionario.getIdade());
		return f;
	}
	
	/**
	 * corrija-me, e quando o banco não permitir a deleção?
	 * configurar mensagem de erro (problema com chave estrangeira) 
	 */
	@Override
	public Funcionario remover(long id) {
		Funcionario f = funcionarioRepository.findById(id).get();
		funcionarioRepository.deleteById(id);
		return f;
	}
	
}
