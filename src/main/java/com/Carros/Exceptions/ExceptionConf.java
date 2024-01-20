package com.Carros.Exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
@Component
@Order
public class ExceptionConf extends ResponseEntityExceptionHandler{
	// Pode passar lista de exceptions
	// Sec acontecer qualquer exception ele retorna esse método
	// Anotação -> mapear exception para método
	
	
	@ExceptionHandler(value={EmptyResultDataAccessException.class, EntityNotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<?> errorNotFound(EmptyResultDataAccessException e) {
	    return ResponseEntity.notFound().build();
	}
	@ExceptionHandler(value={ IllegalArgumentException.class })
	public ResponseEntity<?> errorBadRequest() {
		return ResponseEntity.badRequest().build();
	}
}


