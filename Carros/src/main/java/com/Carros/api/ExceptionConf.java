package com.Carros.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConf{
	// Pode passar lista de exceptions
	// Sec acontecer qualquer exception ele retorna esse método
	// Anotação -> mapear exception para método
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EmptyResultDataAccessException.class})
	public ResponseEntity<?> errorNotFound() {
		String message = "Recurso não encontrado.";
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<?> errorBadRequest() {
		return ResponseEntity.badRequest().build();
	}
}


