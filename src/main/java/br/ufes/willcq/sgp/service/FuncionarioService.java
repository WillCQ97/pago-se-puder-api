package br.ufes.willcq.sgp.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import br.ufes.willcq.sgp.model.Funcionario;

@Validated
public interface FuncionarioService {

	public Iterable<Funcionario> listar();

	public Funcionario buscar(Long id);

	public Funcionario adicionar(Funcionario funcionario);

	public Funcionario atualizar(Long id, Funcionario funcionario);
	
	public List<Funcionario> importar(MultipartFile arquivoCSV);

	public void remover(Long id);

}
