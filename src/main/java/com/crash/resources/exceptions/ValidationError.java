package com.crash.resources.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError extends StandaError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError() {
		super();
	}

	public ValidationError(Date timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message){
		 errors.add(new FieldMessage(fieldName, message));
	}


	
	
}
