package com.ceiba.induccion.utils.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ceiba.induccion.utils.ErrorDetails;

@ControllerAdvice
@RestController
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
	  @ExceptionHandler(Conflicto.class)
	  public final ResponseEntity<ErrorDetails> handleConflictExceptions(Exception ex, WebRequest request) {
		  
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	  }
	  
	  /*@ExceptionHandler(ParametrosInvalidos.class)
	  public final ResponseEntity<ErrorDetails> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }*/
}
