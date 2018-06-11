package br.com.demo.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.demo.error.ResourceNotFoundDetails;
import br.com.demo.error.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException){		
		Date now = new Date();
		
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.builder()
			.withTimestamp(new Long(now.getTime()))
			.withStatus(HttpStatus.NOT_FOUND.value())
			.withTitle("Resource not found.")
			.withDetail(rnfException.getMessage())
			.withDeveloperMessage(rnfException.getClass().getName())
			.build();
		
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND); 
	}
}
