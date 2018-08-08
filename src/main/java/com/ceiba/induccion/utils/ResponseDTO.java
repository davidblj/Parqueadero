package com.ceiba.induccion.utils;

import org.springframework.http.HttpStatus;

public class ResponseDTO {
	
	private HttpStatus estado;
    private String mensaje;
    
	public ResponseDTO(HttpStatus status, String message) {
		this.estado = status;
		this.mensaje = message;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
