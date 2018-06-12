package br.com.demo.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.demo.error.ResourceNotFoundDetails;
import br.com.demo.error.ResourceNotFoundException;
import br.com.demo.error.ValidationErrorDetails;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> MethodArgumentNotValidException(MethodArgumentNotValidException manvException){		
		long l = new Date().getTime();
		
		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		
		ValidationErrorDetails veDetails = ValidationErrorDetails.builder()
			.withTimestamp(l)
			.withStatus(HttpStatus.BAD_REQUEST.value())
			.withTitle("Field validation error.")
			.withDetail("Field validation error.")
			.withDeveloperMessage(manvException.getClass().getName())
			.withField(fields)
			.withFieldMessage(fieldMessages)
			.build();
		
		return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST); 
	}
}
