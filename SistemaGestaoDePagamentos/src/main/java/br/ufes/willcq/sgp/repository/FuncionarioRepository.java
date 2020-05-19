package br.ufes.willcq.sgp.repository;

import org.springframework.data.repository.CrudRepository;

import br.ufes.willcq.sgp.model.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

}
