package com.ceiba.induccion.vehiculos;

import org.springframework.data.annotation.Id;

public class Vehiculo {

	@Id
    private String id;	
	
	private String placa;
	private String tipo;
	
	public Vehiculo() {}
	
	public Vehiculo(String placa, String tipo) { 
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

	public void setTipo(String tipo) throws Exception {
		this.tipo = tipo;
	}
	
	// class utils 
	
	public boolean tipoEsValido() {
		return this.tipo.equals("CARRO") || this.tipo.equals("MOTO");
	}
}
