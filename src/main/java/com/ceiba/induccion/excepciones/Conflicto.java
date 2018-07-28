package com.ceiba.induccion.excepciones;

public class Conflicto extends RuntimeException {

	public Conflicto() {}
	public Conflicto(String mensaje) {
		super(mensaje);
	}
}
