package com.ceiba.induccion.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class Conflicto extends RuntimeException {
			
	public Conflicto() {}
	public Conflicto(String mensaje) {
		super(mensaje);
	}
}
