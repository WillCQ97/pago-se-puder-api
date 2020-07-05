package br.ufes.willcq.sgp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<RespostaErro> handleResourceNotFound(ResourceNotFoundException e) {
		var status = HttpStatus.NOT_FOUND;
		RespostaErro rErro = new RespostaErro();

		rErro.setStatus(status.value());
		rErro.setDataHora(LocalDateTime.now());
		rErro.setMensagem(e.getMessage());
		
		return new ResponseEntity<>(rErro, status);
	}

	/*
	 * Como os métodos no controller foram marcados com @Valid,
	 * as exceptions deixaram de ser ConstraintValidationException
	 * para ser MethodArgumentNotValidException
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RespostaErro> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		
		var status = HttpStatus.BAD_REQUEST;
		RespostaErro repErros = new RespostaErro();
		repErros.setStatus(status.value());
		repErros.setDataHora(LocalDateTime.now());
		repErros.setMensagem("Um ou mais campos informados são inválidos.");

		for (ObjectError objError : e.getBindingResult().getAllErrors()) {
			ItemErro iErro = new ItemErro();

			iErro.setCampo(((FieldError) objError).getField());
			iErro.setMensagem(objError.getDefaultMessage());

			repErros.addError(iErro);
		}

		return new ResponseEntity<>(repErros, status);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<RespostaErro> handleNegocioExcetpion(NegocioException e){
		var status = HttpStatus.BAD_REQUEST;
		
		var resposta = new RespostaErro(status.value(), LocalDateTime.now(), e.getMessage());
		return new ResponseEntity<RespostaErro>(resposta, status);
	}
}
