package com.Carros.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConf{
	// Pode passar lista de exceptions
	// Sec acontecer qualquer exception ele retorna esse método
	// Anotação -> mapear exception para método
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<?> errorNotFound() {
		return ResponseEntity.notFound().build();
	}
	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<?> errorBadRequest() {
		return ResponseEntity.badRequest().build();
	}
}


