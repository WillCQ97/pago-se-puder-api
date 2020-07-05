package br.ufes.willcq.sgp.exception;

import java.time.LocalDateTime;

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
		
		RespostaErro repErros = new RespostaErro();
		
		for (ConstraintViolation cViolation : e.getConstraintViolations()) {
			ItemErro iErro = new ItemErro();
			
			iErro.setCodigo(cViolation.getPropertyPath().toString());
			iErro.setMensagem(cViolation.getMessage());
			iErro.setDataHora(LocalDateTime.now());
			
			repErros.addError(iErro);
		}
		
		return new ResponseEntity<>(repErros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ItemErro> handle(ResourceNotFoundException e) {
		ItemErro iErro = new ItemErro();
		iErro.setDataHora(LocalDateTime.now());
		iErro.setMensagem(e.getMessage());
		return new ResponseEntity<>(iErro, HttpStatus.NOT_FOUND);
	}
	
}
