package com.ceiba.induccion.utils.excepciones;

public class ErrorInternoDelServidor extends RuntimeException  {

	public ErrorInternoDelServidor() {}
	public ErrorInternoDelServidor(String mensaje) {
		super(mensaje);
	}
}
