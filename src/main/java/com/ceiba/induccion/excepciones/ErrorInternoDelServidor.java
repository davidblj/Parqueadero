package com.ceiba.induccion.excepciones;

public class ErrorInternoDelServidor extends RuntimeException  {

	public ErrorInternoDelServidor() {}
	public ErrorInternoDelServidor(String mensaje) {
		super(mensaje);
	}
}
