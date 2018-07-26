package com.ceiba.induccion.vehiculos;

import org.springframework.data.annotation.Id;

public class Vehiculo {

	@Id
    private String id;	
	
	private String placa;
	
	public Vehiculo() {}
	
	public Vehiculo(String placa) { 
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}	
}
