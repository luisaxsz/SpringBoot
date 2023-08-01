package com.Carros.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionConf extends ResponseEntityExceptionHandler{
	// Pode passar lista de exceptions
	// Sec acontecer qualquer exception ele retorna esse método
	// Anotação -> mapear exception para método
	@ExceptionHandler(value={EmptyResultDataAccessException.class})
	public ResponseEntity<?> errorNotFound(EmptyResultDataAccessException e) {
	    return ResponseEntity.notFound().build();
	}
	@ExceptionHandler(value={ IllegalArgumentException.class })
	public ResponseEntity<?> errorBadRequest() {
		return ResponseEntity.badRequest().build();
	}
}


