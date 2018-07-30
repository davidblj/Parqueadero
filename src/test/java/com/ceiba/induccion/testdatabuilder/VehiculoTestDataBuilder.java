package com.ceiba.induccion.testdatabuilder;

import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

public class VehiculoTestDataBuilder {

	private static final String PLACA = "WMQ999";
	private static final String TIPO = Constants.VEHICULO_CARRO;
	
	private String placa;
	private String tipo;
	
	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
	}
	
	public VehiculoModelo build() {
		return new VehiculoModelo(placa, tipo);
	}
	
	public VehiculoTestDataBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
}
