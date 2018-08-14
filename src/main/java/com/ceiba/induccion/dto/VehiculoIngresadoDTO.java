package com.ceiba.induccion.dto;

import java.util.Calendar;

public class VehiculoIngresadoDTO {
	
	private String placa;
	private String tipo;		
	private Calendar fechaDeIngreso;
	
	public VehiculoIngresadoDTO(String placa, String tipo, Calendar fechaDeIngreso) {		
		this.placa = placa;
		this.tipo = tipo;
		this.fechaDeIngreso = fechaDeIngreso;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Calendar getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	public void setFechaDeIngreso(Calendar fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}	
}
