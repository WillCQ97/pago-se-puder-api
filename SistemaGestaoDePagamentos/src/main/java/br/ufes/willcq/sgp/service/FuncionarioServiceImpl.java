package br.ufes.willcq.sgp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.sgp.exception.NegocioException;
import br.ufes.willcq.sgp.exception.ResourceNotFoundException;
import br.ufes.willcq.sgp.model.Funcionario;
import br.ufes.willcq.sgp.model.Pagamento;
import br.ufes.willcq.sgp.repository.FuncionarioRepository;
import br.ufes.willcq.sgp.repository.PagamentoRepository;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	private void verificarFuncionarioCadastrado(long id) {
		if (!funcionarioRepository.existsById(id)) {
			throw new ResourceNotFoundException("Funcionário com id " + id + " não encontrado!");
		}
	}

	private void validarIdade(int idade) {
		if (idade == 0) {
			throw new NegocioException("A idade precisa ser informada e diferente de 0.");
		}
	}

	private void verificarPagamentosCadastrados(long idFuncionario) {

		String msg = "Há pagamentos cadastrados para este funcionário.";
		msg += "Ele não pode ser removido!";

		for (Pagamento p : pagamentoRepository.findAll()) {
			if (p.getSolicitante().getId() == idFuncionario) {
				throw new NegocioException(msg);
			}
			if (p.getAprovador() != null && p.getAprovador().getId() != idFuncionario) {
				throw new NegocioException(msg);
			}
		}
	}

	@Override
	public Iterable<Funcionario> listar() {
		return funcionarioRepository.findAll();
	}

	@Override
	public Funcionario buscar(long id) {
		this.verificarFuncionarioCadastrado(id);
		return funcionarioRepository.findById(id).get();
	}

	@Override
	public Funcionario salvar(Funcionario funcionario) {
		this.validarIdade(funcionario.getIdade());
		return funcionarioRepository.save(funcionario);
	}

	/**
	 * A atualização deve receber os dados de todos os campos
	 */
	@Override
	public Funcionario atualizar(long id, Funcionario funcionario) {
		this.verificarFuncionarioCadastrado(id);
		this.validarIdade(funcionario.getIdade());

		funcionario.setId(id);
		return funcionarioRepository.save(funcionario);
	}

	/**
	 * O funcionário só pode ser removido se não houver pagamentos associados à ele
	 */
	@Override
	public void remover(long id) {
		this.verificarFuncionarioCadastrado(id);
		this.verificarPagamentosCadastrados(id);
		funcionarioRepository.deleteById(id);
	}

}
