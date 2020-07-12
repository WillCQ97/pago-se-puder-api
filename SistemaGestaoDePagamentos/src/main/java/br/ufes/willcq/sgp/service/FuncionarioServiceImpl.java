package br.ufes.willcq.sgp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
		if (id <= 0) {
			throw new NegocioException("O id informado para o funcionário deve ser maior que zero.");
		}
		if (!funcionarioRepository.existsById(id)) {
			throw new ResourceNotFoundException("Funcionário com id " + id + " não encontrado!");
		}
	}

	private void validarIdade(int idade) {
		if (idade == 0) {
			throw new NegocioException("A idade precisa ser informada e diferente de zero.");
		}
	}

	private void verificarPagamentosCadastrados(long idFuncionario) {

		String msg = "Há pagamentos cadastrados para este funcionário. ";
		msg += "Ele não pode ser removido!";

		for (Pagamento p : pagamentoRepository.findAll()) {
			if (p.getSolicitante().getId() == idFuncionario) {
				throw new NegocioException(msg);
			}
			if (p.getAprovador() != null && p.getAprovador().getId() == idFuncionario) {
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
	public Funcionario adicionar(Funcionario funcionario) {
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
	
	/*
	 * Leitura de informações dos dados de um arquivo csv,
	 * instancia os funcionarios e os salva
	 */
	@Override
	public List<Funcionario> importar(MultipartFile arquivoCSV) {
		
		List<Funcionario> funcionarios = new ArrayList<>();
		List<Exception> erros = new ArrayList<>();
		
		try {
			Scanner scanFile = new Scanner(arquivoCSV.getInputStream());
			String linha = scanFile.nextLine();
			
			while(scanFile.hasNext()) {
				linha = scanFile.nextLine();
				Scanner scanLine = new Scanner(linha);
				scanLine.useDelimiter(",");
				
				while(scanLine.hasNext()) {
					String nome = scanLine.next().replace("\"", "");
                    String cargo = scanLine.next().replace("\"", "");
                    int idade = Integer.parseInt(scanLine.next());
                    int nFaltas = Integer.parseInt(scanLine.next());
                    
                    Funcionario f = new Funcionario(nome, cargo, idade, nFaltas);
                    funcionarios.add(f);
				}
				scanLine.close();
			}
			scanFile.close();
			
		}catch(IOException | NumberFormatException ex) {
			erros.add(ex);
		}
		
		if(funcionarios.size() == 0) {
			throw new NegocioException("Nenhum dado de funcionário foi importado. Verifique o arquivo escolhido.");
		}
		
		funcionarioRepository.saveAll(funcionarios);
		return funcionarios;
	}
}
