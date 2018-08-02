package com.ceiba.induccion.utils.factura;

public class FacturaDTO {
	
	int horas;
	int dias;
	float precio;	
	
	public FacturaDTO(int horas, int dias, float precio) {
		super();
		this.horas = horas;
		this.dias = dias;
		this.precio = precio;
	}
	
	public int getHoras() {
		return horas;
	}
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public int getDias() {
		return dias;
	}
	
	public void setDias(int dias) {
		this.dias = dias;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}	
}
