package com.ceiba.induccion.utils.excepciones;

public class ParametrosInvalidos extends RuntimeException {	

	public ParametrosInvalidos() {}
	public ParametrosInvalidos(String mensaje) {
		super(mensaje);
	}
}
