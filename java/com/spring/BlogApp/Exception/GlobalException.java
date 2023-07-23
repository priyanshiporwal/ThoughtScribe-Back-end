package com.spring.BlogApp.Exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.BlogApp.Payloads.ApiResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFound(ResourceNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=ConstraintViolationException.class)
	public ResponseEntity<String> constraintViolation(ConstraintViolationException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value=SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponse> duplicateEntry(SQLIntegrityConstraintViolationException e){
		ApiResponse apiResponse=new ApiResponse("Account already exists!","500");
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
