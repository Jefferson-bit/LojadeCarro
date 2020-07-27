package com.crash.resources.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
