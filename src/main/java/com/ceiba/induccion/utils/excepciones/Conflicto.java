package com.ceiba.induccion.utils.excepciones;

import org.springframework.web.bind.annotation.ResponseStatus;

public class Conflicto extends RuntimeException {
			
	public Conflicto() {}
	public Conflicto(String mensaje) {
		super(mensaje);
	}
}
