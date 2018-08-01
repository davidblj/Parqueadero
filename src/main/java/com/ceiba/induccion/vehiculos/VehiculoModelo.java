package com.ceiba.induccion.vehiculos;

import java.util.Calendar;
import java.util.Date;

import com.ceiba.induccion.utils.Constants;

public class VehiculoModelo {

	String placa;
	String tipo;
	Calendar fechaDeIngreso;	

	int cilindraje;
	
	// private Date fechaDeIngreso
	
	public VehiculoModelo(String placa, String tipo, int cilindraje) {
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
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
	
	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	public Calendar getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public void setFechaDeIngreso(Calendar fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}
	
	public boolean esMoto() {
		return this.tipo.equals(Constants.VEHICULO_MOTO);
	}

	public boolean esCarro() {
		return this.tipo.equals(Constants.VEHICULO_CARRO);
	}		
	
	// cilindraje > 550 (con herencia)
}
