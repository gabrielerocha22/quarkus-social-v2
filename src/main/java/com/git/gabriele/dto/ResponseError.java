package com.git.gabriele.dto;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;

public class ResponseError {
	
	public static final int UNPROCESSABLE_ENTITY_STATUS = 422;

<<<<<<< HEAD
	
	private String message;
	private Collection<FieldError>errors;
	

=======
	private String message;
	private Collection<FieldError>errors;
	
>>>>>>> 6f609a0 (add exceptions)
	public ResponseError(String message, Collection<FieldError> errors) {
		this.message = message;
		this.errors = errors;
	}
	
<<<<<<< HEAD
	
=======
>>>>>>> 6f609a0 (add exceptions)
	public static <T> ResponseError createFromValidation(Set<ConstraintViolation<T>> violations) {
		List<FieldError> errors = violations.stream().map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage())).collect(Collectors.toList());
		
		String message = "validation error";
		var responseError = new ResponseError(message, errors);
		return responseError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Collection<FieldError> getErrors() {
		return errors;
	}
	public void setErrors(Collection<FieldError> errors) {
		this.errors = errors;
	}
<<<<<<< HEAD
	
	public Response withStatusCode(int code) {
		return Response.status(code).entity(this).build();
	}
	
	
=======
	public Response withStatusCode(int code) {
		return Response.status(code).entity(this).build();
	}
>>>>>>> 6f609a0 (add exceptions)
}
