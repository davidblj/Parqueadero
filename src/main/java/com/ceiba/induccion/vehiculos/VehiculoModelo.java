package com.ceiba.induccion.vehiculos;

import java.util.Calendar;

import com.ceiba.induccion.utils.Constants;

public class VehiculoModelo {

	String placa;
	String tipo;
	Calendar fechaDeIngreso;
	Calendar fechaDeSalida;	
	int cilindraje;
	
	public VehiculoModelo(String placa, String tipo, int cilindraje, Calendar fechaDeIngreso, Calendar fechaDeSalida) {
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.fechaDeIngreso = fechaDeIngreso;
		this.fechaDeSalida = fechaDeSalida;
	}
	
	public VehiculoModelo(String placa, String tipo, int cilindraje, Calendar fechaDeIngreso) {
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.fechaDeIngreso = fechaDeIngreso;
	}
	
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

	public Calendar getFechaDeSalida() {
		return fechaDeSalida;
	}

	public void setFechaDeSalida(Calendar fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}
	
	public boolean esMoto() {
		return this.tipo.equals(Constants.VEHICULO_MOTO);
	}

	public boolean esCarro() {
		return this.tipo.equals(Constants.VEHICULO_CARRO);
	}
}
