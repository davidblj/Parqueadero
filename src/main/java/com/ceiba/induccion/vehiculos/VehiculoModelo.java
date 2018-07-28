package com.ceiba.induccion.vehiculos;


public class VehiculoModelo {
	
	String placa;
	String tipo;
	
	public VehiculoModelo(String placa, String tipo) {
		this.placa = placa;
		this.tipo = tipo;
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
}
