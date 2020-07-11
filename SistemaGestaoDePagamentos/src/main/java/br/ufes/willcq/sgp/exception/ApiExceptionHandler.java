package br.ufes.willcq.sgp.exception;

import java.time.OffsetDateTime;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	private ResponseEntity<RespostaErro> criarRespostaErro(HttpStatus status, String mensagem){
		var respostaErro = new RespostaErro(status.value(), OffsetDateTime.now(), mensagem);
		return new ResponseEntity<>(respostaErro, status);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<RespostaErro> handleResourceNotFound(ResourceNotFoundException e) {
		return this.criarRespostaErro(HttpStatus.NOT_FOUND, e.getMessage());
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
		repErros.setDataHora(OffsetDateTime.now());
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
		return this.criarRespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<RespostaErro> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		var msg = "Verifique os dados informados estão corretamente formatados.";
		return this.criarRespostaErro(HttpStatus.BAD_REQUEST, msg);
	}
	
	@ExceptionHandler(FileSizeLimitExceededException.class)
	public ResponseEntity<RespostaErro> handlerFileSizeExceededException(FileSizeLimitExceededException e){
		var msg = "Tamanho máximo de arquivo para importação é de 10MB";
		return this.criarRespostaErro(HttpStatus.BAD_REQUEST, msg);
	}
}
