package com.crash.resources.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crash.service.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
		
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandaError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
		String err = "Resource not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandaError error = new StandaError(new Date(), status.value(), err, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandaError> validation(MethodArgumentNotValidException ex, HttpServletRequest request){
		String err = "Constraint Validation";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError(new Date(), status.value(), err, "Validation Error", request.getRequestURI());
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(error);
	}
	
}
