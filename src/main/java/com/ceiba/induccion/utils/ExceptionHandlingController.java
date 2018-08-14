package com.ceiba.induccion.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ceiba.induccion.utils.excepciones.ParametrosInvalidos;

@ControllerAdvice(annotations = RestController.class)		
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
	  @ExceptionHandler(ParametrosInvalidos.class)
	  public final ResponseEntity<ErrorDetails> handleConflictExceptions(ParametrosInvalidos ex, WebRequest request) {
		  
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }	  	  
}
