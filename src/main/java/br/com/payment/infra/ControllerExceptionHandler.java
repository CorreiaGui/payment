package br.com.payment.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.payment.dtos.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionDto> threatDuplicateEntry(DataIntegrityViolationException exception) {
		ExceptionDto dto = new ExceptionDto("Usuário já cadastrado", "400");
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionDto> threatNotFound(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDto> threatGeneral(Exception exception) {
		ExceptionDto dto = new ExceptionDto(exception.getMessage(), "500");
		return ResponseEntity.internalServerError().body(dto);
	}
}
