package br.com.demo.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.demo.error.ErrorDetails;
import br.com.demo.error.ResourceNotFoundDetails;
import br.com.demo.error.ResourceNotFoundException;
import br.com.demo.error.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException) {
		Date now = new Date();

		ErrorDetails rnfDetails = ResourceNotFoundDetails.builder().timestamp(new Long(now.getTime()))
				.status(HttpStatus.NOT_FOUND.value()).title("Resource not found.").detail(rnfException.getMessage())
				.developerMessage(rnfException.getClass().getName()).build();

		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		long l = new Date().getTime();

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));

		ErrorDetails veDetails = ValidationErrorDetails.builder().field(fields).fieldMessage(fieldMessages).timestamp(l)
				.status(status.value()).title("Field validation error.")
				.detail(ex.getMessage()).developerMessage(ex.getClass().getName()).build();

		return new ResponseEntity<>(veDetails, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Date now = new Date();

		ErrorDetails errorDetails = ErrorDetails.builder().timestamp(new Long(now.getTime())).status(status.value())
				.title("Internal exception").detail(ex.getMessage()).developerMessage(ex.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, headers, status);
	}
}
