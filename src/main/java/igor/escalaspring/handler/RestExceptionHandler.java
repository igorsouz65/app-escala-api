package igor.escalaspring.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import igor.escalaspring.error.ErrorDetails;
import igor.escalaspring.error.ResourceNotFoundDetails;
import igor.escalaspring.error.ResourceNotFoundException;
import igor.escalaspring.error.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException){
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.builder()
				.withTimestamp(new Date().getTime())
				.withStatus(HttpStatus.NOT_FOUND.value())
				.withTitle("Resource not found")
				.withDetail(rnfException.getMessage())
				.withDeveloperMessage(rnfException.getClass().getName())
				.build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException manvException, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErros = manvException.getBindingResult().getFieldErrors();
		String fields = fieldErros.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		ValidationErrorDetails veDetails = ValidationErrorDetails.builder()
				.withTimestamp(new Date().getTime())
				.withStatus(HttpStatus.BAD_REQUEST.value())
				.withTitle("Field Validation Error")
				.withDetail("Field Validation Error")
				.withDeveloperMessage(manvException.getClass().getName())
				.withField(fields)
				.withFieldMessage(fieldMessages)
				.build();
		return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails errorDetails = ErrorDetails.newBuilder()
				.withTimestamp(new Date().getTime())
				.withStatus(status.value())
				.withTitle("Internal Exception")
				.withDetail(ex.getMessage())
				.withDeveloperMessage(ex.getClass().getName())
				.build();
		
		return new ResponseEntity<>(errorDetails, headers, status);
	}
	
}
