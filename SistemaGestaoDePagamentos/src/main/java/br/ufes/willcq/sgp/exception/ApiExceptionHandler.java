package br.ufes.willcq.sgp.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<RespostaErro> handle(ConstraintViolationException e) {
		
		RespostaErro erros = new RespostaErro();
		
		for (ConstraintViolation violation : e.getConstraintViolations()) {
			ItemErro erro = new ItemErro();
			erro.setCodigo(violation.getMessageTemplate());
			erro.setMensagem(violation.getMessage());
			erros.addError(erro);
		}
		
		return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ItemErro> handle(ResourceNotFoundException e) {
		ItemErro erro = new ItemErro();
		erro.setMensagem(e.getMessage());
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}
	
}
