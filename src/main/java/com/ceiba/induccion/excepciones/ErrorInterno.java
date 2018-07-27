package com.ceiba.induccion.excepciones;

public class ErrorInterno extends RuntimeException  {

	public ErrorInterno() {}
	public ErrorInterno(String mensaje) {
		super(mensaje);
	}
}
