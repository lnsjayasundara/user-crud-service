package com.crud.springboot.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
public class ApiMessage {
	private HttpStatus status;
    private String message;
    private List<String> errors;
    
	public ApiMessage(HttpStatus status, String message, List<String> errors) {
		super(); 
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
    
	public ApiMessage(HttpStatus status) {
		super();
		this.status = status;
	}
	public ApiMessage(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
    
}
