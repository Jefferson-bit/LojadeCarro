package com.crash.resources.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crash.service.exceptions.AuthorizationException;
import com.crash.service.exceptions.DataBaseException;
import com.crash.service.exceptions.FileException;
import com.crash.service.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandaError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		String err = "Resource not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandaError error = new StandaError(new Date(), status.value(), err, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandaError> database(DataBaseException ex, HttpServletRequest request) {
		String err = "Error data";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandaError error = new StandaError(new Date(), status.value(), err, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandaError> authorization(AuthorizationException ex, HttpServletRequest request) {
		String err = "Error Forbidden";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandaError error = new StandaError(new Date(), status.value(), err, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(SizeLimitExceededException.class)
	public ResponseEntity<StandaError> file(SizeLimitExceededException ex, HttpServletRequest request) {
		String err = "Error File";
		HttpStatus status = HttpStatus.EXPECTATION_FAILED;
		StandaError error = new StandaError(new Date(), status.value(), err, "erro no arquivo", request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandaError> validation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		String err = "Constraint Validation";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError(new Date(), status.value(), err, "Validation Error",
				request.getRequestURI());
		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(error);
	}

}
