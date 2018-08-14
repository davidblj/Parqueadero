package com.ceiba.induccion.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParametrosInvalidos extends RuntimeException {	

	public ParametrosInvalidos() {}
	public ParametrosInvalidos(String mensaje) {
		super(mensaje);
	}
}
