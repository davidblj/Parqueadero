package com.ceiba.induccion.testdatabuilder;

import java.util.Calendar;

import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.utils.Constants;

public class VehiculoTestDataBuilder {

	private static final String PLACA = "WMQ999";
	private static final String TIPO = Constants.VEHICULO_CARRO;
	private static final int CILINDRAJE = 0;
	
	private String placa;
	private String tipo;
	private int cilindraje;
	private Calendar fechaDeIngreso;
	private Calendar fechaDeSalida;
	
	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
		this.cilindraje = CILINDRAJE;
	}
	
	public VehiculoModelo build() {
		return new VehiculoModelo(placa, tipo, cilindraje, fechaDeIngreso, fechaDeSalida);
	}
	
	public VehiculoTestDataBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	
	public VehiculoTestDataBuilder conFechaDeIngreso(Calendar fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
		return this;
	}
	
	public VehiculoTestDataBuilder conFechaDeSalida(Calendar fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
		return this;
	}
}
